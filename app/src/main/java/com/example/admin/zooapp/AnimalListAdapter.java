package com.example.admin.zooapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/11/2017.
 */

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder> {

    List<Animal> animalList = new ArrayList<>();
    Context context;

    public AnimalListAdapter(List<Animal> animalList){
        this.animalList = animalList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView animalName;

        public ViewHolder(View itemView){
            super(itemView);

            animalName = (TextView) itemView.findViewById(R.id.animalName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Animal animal = animalList.get(position);
        holder.animalName.setText(animal.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AnimalDisplay.class);
                intent.putExtra("Animal", animal);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return animalList.size();
    }


}
