package com.example.whatsinmyfridge2.objects;

import java.util.ArrayList;
import java.util.List;

public class Fridge {
    public static ArrayList<Item> databaseOfItems = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<RecipeCard> recipes = new ArrayList<>();
    public static ArrayList<RecipeCard> filteredRecipes = new ArrayList<>();

    public static ArrayList<Item> getDatabaseOfItems() {
        return (ArrayList<Item>) databaseOfItems.clone();
    }

    public static ArrayList<Item> getItems() {
        return (ArrayList<Item>) items.clone();
    }

    public static ArrayList<RecipeCard> getRecipes() {
        return (ArrayList<RecipeCard>) recipes.clone();
    }

    public static void setFilteredRecipes(ArrayList<RecipeCard> filteredRecipes) {
        Fridge.filteredRecipes = filteredRecipes;
    }

    public static ArrayList<RecipeCard> getFilteredRecipes() {
        return filteredRecipes;
    }

    public static void addItem(Item i) {
        if (!items.contains(i))
            items.add(i);
    }

    public static Item getItem(int i) {
        return items.get(i);
    }

    public static boolean deleteItem(Item i) {
        return items.remove(i);
    }

    public static Item deleteItem(int i) {
        return items.remove(i);
    }

    public static void addItemToDb(Item i) {
        if (!databaseOfItems.contains(i))
            databaseOfItems.add(i);
    }

    public static Item getItemToDb(int i) {
        return databaseOfItems.get(i);
    }

    public static boolean deleteItemToDb(Item i) {
        return databaseOfItems.remove(i);
    }

    public static Item deleteItemToDb(int i) {
        return databaseOfItems.remove(i);
    }

    public static void addRecipe(RecipeCard r) {
        if (!recipes.contains(r))
            recipes.add(r);
    }

    public static void addFilteredRecipe(RecipeCard r){
        if (!filteredRecipes.contains(r)){
            filteredRecipes.add(r);
        }
    }


    public static RecipeCard addRecipe(int r) {
        return recipes.get(r);
    }

    public static boolean deleteRecipe(RecipeCard r) {
        return recipes.remove(r);
    }

    public static RecipeCard deleteRecipe(int i) {
        return recipes.remove(i);
    }
    // Podes adicionar os filtros aqui : )
    // Porque static?
    // Porque assim nao tens de inicializar
    // Se for precisos mais metodos digam ou insiram : )
    // Cheats!!

}
