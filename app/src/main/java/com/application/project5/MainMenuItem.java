package com.application.project5;
/**
 * Class that defines a main menu item
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class MainMenuItem {
    private String item;
    private int imageResource;

    /**
     * constructor
     * @param item the string part of the main menu item
     * @param image the image part of the main menu item
     */
    public MainMenuItem(String item, int image) {
        this.imageResource=image;
        this.item=item;
    }

    /**
     * getter for the image resource
     * @return the image resource
     */
    public int getImageResource(){
        return imageResource;
    }

    /**
     * getter for the item
     * @return the main menu item
     */
    public String getItem(){
        return item;
    }
}
