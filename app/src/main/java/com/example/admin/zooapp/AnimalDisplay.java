package com.example.admin.zooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalDisplay extends AppCompatActivity {

    Animal animal;
    TextView name,sci_name,habitat,diet,description;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_display);

        name = (TextView) findViewById(R.id.tvName);
        sci_name = (TextView) findViewById(R.id.tvSciName);
        habitat =(TextView) findViewById(R.id.tvHabitat);
        diet = (TextView) findViewById(R.id.tvDiet);
        description = (TextView) findViewById(R.id.tvDescription);

        Intent intent = getIntent();
        animal = (Animal) intent.getSerializableExtra("Animal");

        name.setText(animal.getName());
        sci_name.setText(animal.getSci_name());
        habitat.setText(animal.getHabitat());
        diet.setText(animal.getDiet());
        description.setText(animal.getDescription());

    }
}
