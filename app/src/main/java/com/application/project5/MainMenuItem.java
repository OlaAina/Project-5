package com.application.project5;

public class MainMenuItem {
    private String item;
    private int imageResource;

    public MainMenuItem(String item, int image) {
        this.imageResource=image;
        this.item=item;
    }
    public int getImageResource(){
        return imageResource;
    }
    public String getItem(){
        return item;
    }
}
