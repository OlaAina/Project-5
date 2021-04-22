package com.application.project5;

import android.content.res.Resources;

/**
 * Class that describes object donuts, extends class MenuItem
 * This class consists of:
 * A method to calculate and set price
 * A method to check if equal
 * A method to display string representation of object donuts
 * Getter and setter methods
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class Donuts extends MenuItem {

	final static Double donutPrice = 1.39;
	String flavor;
	 
	/**
	 * 	Method to calculate and set the price of a coffee depending on doughnut type
	 */
	@Override
	public void itemPrice() {
		setItemPrice(donutPrice);
    }
	
	/**
	 * Method to check if two doughnuts are equal
	 * @param obj, an object to compare
	 * @return true if equal, otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donuts)
		{
			Donuts donut= (Donuts) obj;
			if(donut.flavor.equals(this.flavor)) {
				if(donut.getItemPrice() == this.getItemPrice()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method to display string representation of doughnut
	 * @return string representation of doughnut
	 */
	@Override
	public String toString() {
		return "Donut:: Flavor: " + flavor +  " :: " + "Quantity: "+ this.getQuantity();
	}


	/**
	 * Get the flavor of doughnut
	 * @return flavor
	 */
	public String getFlavor() {
		return flavor;
	}

	/**
	 * Set the flavor with a string
	 * @param flavor, string representation of the type of doughnut
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	
	
}
