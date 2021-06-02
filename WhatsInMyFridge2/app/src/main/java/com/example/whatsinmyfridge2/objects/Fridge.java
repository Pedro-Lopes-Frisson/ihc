package com.example.whatsinmyfridge2.objects;

import java.util.ArrayList;
import java.util.List;

public class Fridge {
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<RecipeCard> recipes = new ArrayList<>();

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static ArrayList<RecipeCard> getRecipes() {
        return recipes;
    }
    public static void addItem(Item i) {
        items.add(i);
    }

    public static void addRecipe(RecipeCard r) {
        recipes.add(r);
    }

    public static Item getItem(int i) {
        return items.get(i);
    }

    public static RecipeCard addRecipe(int r) {
        return recipes.get(r);
    }

    public static boolean deleteItem(Item i) {
        return items.remove(i);
    }

    public static Item deleteItem(int i) {
        return items.remove(i);
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
