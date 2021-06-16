package com.example.whatsinmyfridge.ObjectDeclaration;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private String name;
    private int ID;
    private double weight;
    private int weightJump;
    private String type;
    private String Image;


    public Item(String name, int ID, double weight, int weightJump, String type, String im) {
        this.name = name;
        this.ID = ID;
        this.weight = weight;
        this.weightJump = weightJump;
        this.type = type;
        this.Image = im;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ID == item.ID &&
                weight == item.weight &&
                weightJump == item.weightJump &&
                Objects.equals(name, item.name) &&
                Objects.equals(type, item.type);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, ID, weight, weightJump, type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightJump() {
        return weightJump;
    }

    public void setWeightJump(int weightJump) {
        this.weightJump = weightJump;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", weight=" + weight +
                ", weightJump=" + weightJump +
                ", type='" + type + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }

    public void setType(String type) {
        this.type = type;
    }

}
