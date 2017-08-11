package com.example.admin.zooapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lv = (ListView) findViewById(R.id.categoryListView);
        categories = new ArrayList<String>();
        initCategoryList();
        setSelection(categories);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), RecycleActivity.class);
                intent.putExtra("Category", parent.getItemAtPosition(position).toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    private void initCategoryList(){
        categories.add("Aquatic");
        categories.add("Birds");
        categories.add("Invertebrates");
        categories.add("Mammals");
        categories.add("Reptiles");
    }


    public void setSelection(ArrayList<String> categories){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, categories.toArray(new String[0]));
            lv.setAdapter(adapter);
        }
        catch (Exception ex){}
    }
}

