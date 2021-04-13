package com.application.project5;
/**
 * Superclass that describes object MenuItem 
 * This class consists of:
 * A method to calculate and set price
 * A method to check if equal
 * A method to display string representation of object donuts
 * Getter and setter methods
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class MenuItem {
	private double itemPrice;
	private int quantity;
	private double totalPrice;
	
	/**
	 * Method to calculate item price
	 */
	public void itemPrice(){

	}
	
	/**
	 * Method to calculate price including quantity
	 */
	public void totalPrice() {
		this.totalPrice = itemPrice * quantity;
	}
	
	/**
	 * Get the total price 
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Check of two objects are	equal
	 * @param obj, an object to compare
	 * @return true if equal otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	/**
	 * Display string representation of menu item
	 * @return string
	 */
	@Override
	public String toString() {
		return null;
	}
	/**
	 * Get the price of the Menu item
	 * @return itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}
	/**
	 * set item price 
	 * @param itemPrice, the price of the item
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * get quantity of item
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * set the quantity 
	 * @param quantity, the quantity of item
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
