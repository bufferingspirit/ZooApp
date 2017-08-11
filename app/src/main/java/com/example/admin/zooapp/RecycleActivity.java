package com.example.admin.zooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    List<Animal> animalList = new ArrayList<>();
    RecyclerView rvAnimalList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    AnimalListAdapter animalListAdapter;
    String category;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        rvAnimalList = (RecyclerView) findViewById(R.id.rvAnimalList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvAnimalList.setLayoutManager(layoutManager);

        rvAnimalList.setItemAnimator(itemAnimator);

        animalListAdapter = new AnimalListAdapter(animalList);
        rvAnimalList.setAdapter(animalListAdapter);

        Intent intent = getIntent();
        category = (String) intent.getSerializableExtra("Category");
        initAnimalList();
        animalListAdapter.notifyDataSetChanged();

        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();
    }

    //this needs to get all animals in category
    private void initAnimalList(){
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Crab", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));
        animalList.add(new Animal("Gator", "Ali", "Woods", "People", "Scary", null, "Reptiles"));

    }
}
