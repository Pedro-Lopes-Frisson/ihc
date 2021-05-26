package com.example.whatsinmyfridge.objects;

public class ListCard {
    private int lImageResource;
    private String lText1;
    private String lText2;

    public ListCard(int image, String text1, String text2){
        lImageResource = image;
        lText1 = text1;
        lText2 = text2;
    }

    public int getlImageResource() {
        return lImageResource;
    }

    public String getlText1() {
        return lText1;
    }

    public String getlText2() {
        return lText2;
    }
}

//Objeto cartas da Shopping List.