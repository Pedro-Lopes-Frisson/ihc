package com.example.whatsinmyfridge2.objects;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Objects;


public class Item implements Parcelable {
    private String name;
    private int ID;
    private double weight;
    private int weightJump;
    private Type type;               // tyoe se é carne e assim
    private WeightMeasure weightMeasure; // se e em gramas (Ricardo)
    private String Image;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Item(String name, int ID, double weight, int weightJump, Type type, String im) {
        this.name = name;
        this.ID = ID;
        this.weight = weight;
        this.weightJump = weightJump;
        this.type = type;
        this.Image = im;
        this.weightMeasure = weightMeasure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(ID);
        dest.writeDouble(weight);
        dest.writeInt(weightJump);
        dest.writeString(type.name());
        dest.writeString(weightMeasure.name());
        dest.writeString(Image);
    }


    public Item(String name, int ID, int weight, int weightJump, Type type, String im,WeightMeasure weightMeasure) {
        this.name = name;
        this.ID = ID;
        this.weight = weight;
        this.weightJump = weightJump;
        this.type = type;
        this.Image = im;
        this.weightMeasure = weightMeasure;
    }

    // Why protected
    protected Item(Parcel in) {
        name = in.readString();
        ID = in.readInt();
        weight = in.readInt();
        weightJump = in.readInt();
        type = Type.valueOf(in.readString());
        weightMeasure= WeightMeasure.valueOf(in.readString());
        Image = in.readString();
    }


    // Object specific overrides


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
                type == item.type &&
                weightMeasure == item.weightMeasure &&
                Objects.equals(Image, item.Image);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, ID, weight, weightJump, type, weightMeasure, Image);
    }

    // Getters e setters Obviously bad but who cares
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

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getWeightJump() {
        return weightJump;
    }

    public void setWeightJump(int weightJump) {
        this.weightJump = weightJump;
    }

    public Type getType() {
        return type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    // Getters Setters end
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

    public void setType(Type type) {
        this.type = type;
    }

}
