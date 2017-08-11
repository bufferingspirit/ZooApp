package com.example.admin.zooapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "Zoo.sql";
    public final static String DATABASE_PATH = "/data/data/com.example.admin.zooapp/databases/";

     /*CREATE TABLE "Zoo" (
	`Animal`	TEXT,
	`Scientific Name`	TEXT,
	`Habitat`	TEXT,
	`Diet`	TEXT,
	`Description`	TEXT,
	`Photo`	BLOB,
	`Category`	TEXT,
	PRIMARY KEY(`Animal`)
);*/

    public static final String TABLE_NAME = "Zoo";
    public static final String ANIMAL_NAME = "Animal";
    public static final String SCI_NAME = "'Scientific Name'";
    public static final String HABITAT = "Habitat";
    public static final String DIET = "Diet";
    public static final String DESCRIPTION = "Description";
    public static final String PHOTO = "Photo";
    public static final String CATEGORY = "Category";

    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;

    }

    //Create a empty database on the system
    public void createDatabase() throws IOException
    {

        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase(myContext);
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }
    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }
    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase(Context context) throws IOException
    {

        InputStream mInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
        Log.v("DB copy", "DB Copied");
    }

    //delete database
    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }
    //Open database
    public void openDatabase() throws SQLException {
        String dbPath = myContext.getDatabasePath(DATABASE_NAME).getPath();
        if (myDataBase != null && myDataBase.isOpen()) {
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        Log.v("DB Open", "DB Opened");
    }



    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

    }


    public Animal getEntry(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + ANIMAL_NAME + " = '" + name + "'";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()) {
            return new Animal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5),cursor.getString(6));
        }
        else{
            return new Animal("","","","","", null,"");
        }
    }

    public ArrayList<Animal> getEntryByCategory(String category){
        ArrayList<Animal>animalList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + CATEGORY + " = '" + category + "'";
        Log.v("DB Query", "Begin Query");
        Cursor cursor = database.rawQuery(query, null);
        Log.v("DB Query", "Finish Query");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Animal animal = new Animal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5),cursor.getString(6));
                animalList.add(animal);
            } while (cursor.moveToNext());
        }
        else{
            Log.v("DB Query", "FAIL");
            return null;
        }
        cursor.close();
        return animalList;
    }


}