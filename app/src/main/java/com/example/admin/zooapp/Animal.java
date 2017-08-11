package com.example.admin.zooapp;

import java.io.Serializable;

/**
 * Created by Admin on 8/11/2017.
 */

public class Animal implements Serializable{
    String name;
    String sci_name;
    String habitat;
    String diet;
    String description;
    byte[] photo;
    String category;
    String sound;

    public Animal(String name, String sci_name, String habitat, String diet, String description, byte[] photo, String category) {
        this.name = name;
        this.sci_name = sci_name;
        this.habitat = habitat;
        this.diet = diet;
        this.description = description;
        this.photo = photo;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSci_name() {
        return sci_name;
    }

    public void setSci_name(String sci_name) {
        this.sci_name = sci_name;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
