package com.application.project5;
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
	
	String donutType; 
	String flavor; 
	
	final static String YEAST_DONUT = "Yeast";
	final static Double YEAST_DONUT_PRICE = 1.39;
	final static String CAKE_DONUT = "Cake";
	final static Double CAKE_DONUT_PRICE = 1.59;
	final static String DONUT_HOLE = "Donut Hole";
	final static Double DONUT_HOLE_PRICE = 0.33;
	 
	/**
	 * 	Method to calculate and set the price of a coffee depending on doughnut type
	 */
	@Override
	public void itemPrice() {
		
		if(donutType.equals(YEAST_DONUT)) {
			setItemPrice(YEAST_DONUT_PRICE);
			
		}
		
		else if(donutType.equals(CAKE_DONUT)) {
			setItemPrice(CAKE_DONUT_PRICE);

		}
		
		else {
			setItemPrice(DONUT_HOLE_PRICE);
		}
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
			if(donut.donutType.equals(this.donutType)) {
				if(donut.flavor.equals(this.flavor)) {
					if(donut.getItemPrice() == this.getItemPrice()) {
						return true; 
					}
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
		return "Donut Type: " + donutType + " :: " + "Flavor: " + flavor + " :: " + "Quantity: " + this.getQuantity();
	}

	/**
	 * Method to get the doughnut type
	 * @return donutType
	 */
	public String getDonutType() {
		return donutType;
	}

	/**
	 * Set doughnut type with a string
	 * @param donutType, string representation of the type of doughnut
	 */
	public void setDonutType(String donutType) {
		this.donutType = donutType;
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
