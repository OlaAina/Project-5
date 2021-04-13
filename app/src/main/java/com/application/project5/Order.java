package com.application.project5;

/**
 * This class defines the order object
 * The class consists of:
 * A method to find a menu item
 * A method to grow the order
 * A method to add and remove a menu item to the order
 * Setters and Getters
 * @author Adeola Adebanjo, Olaolu Aina
 */
public class Order implements Customizable {
	
	
	private MenuItem[] order; 
	private int numofItems; 
	private int orderNumber;
	private double price;

	static final int COULDNT_FIND=-1;
	
	/**
	 * Helper method that finds a given Menu item
	 * @param obj Menu item to be found
	 * @return the index where the Menu item is found
	 */
	private int find(Object obj){
		MenuItem item=(MenuItem) obj;
		int empty=0;
		for(int i=0;i<order.length;i++) {
			if(order[i]!=null) {
				empty++;
			}
		}
		if(empty==0) {
			return COULDNT_FIND;
		}
		
			
		for(int i=0;i<order.length-1;i++) {
				if(order[i]!=null) {
					if(item.equals(order[i]))
					{
						return i;
					}
				}
			}
			return COULDNT_FIND;
		} 
	/**
	 * This method increases a full array by the size of 4 
	 */
	static final int GROWTH=4;
	private void grow() {
		int currentSpace= this.order.length;
		MenuItem[] extraSpace= new MenuItem[currentSpace+GROWTH];
		for (int i=0;i<this.order.length;i++) {
			extraSpace[i]=this.order[i];
		}
		this.order= extraSpace;

	}

	/**
	 * This method adds a menu item to the order
	 * @param obj menu item to be added
	 * @return if the menu item was added
	 */
	@Override
	public boolean add(Object obj) {
		MenuItem item= (MenuItem) obj;
		int foundSpace=0;
			for (int i = 0; i < order.length; i++) {
				if (order[i] == null) {
					order[i] = item;
					foundSpace = 1;
					break;
				}
			}
			if (foundSpace == 0) {
				this.grow();
				order[numofItems] = item;
			}
			this.numofItems++;
			this.price += ((MenuItem) obj).getTotalPrice();
			return true;
		}


	/**
	 * Removes a menu item
	 * @param item the menu item to be removed
	 * @return if the item was truly removed
	 */
	@Override
	public boolean remove(Object item) {
		int removeThis;
		removeThis=this.find(item);
		
		if(removeThis == -1) {
			return false;
		}
		else {
		this.order[removeThis] = null;
		for(int i = removeThis; i < order.length-1; i++)
		{
			order[i]=order[i+1];
			
		}
			order[numofItems-1] = null;
			this.numofItems--;
			return true;
	}
	}

	/**
	 * Gets the order number
	 * @return The order number
	 */
	public int getOrderNumber() {
		return this.orderNumber;
	}

	/**
	 * Gets the order price
	 * @return the order price
	 */
	public double getOrderPrice(){
		return this.price;
	}

	/**
	 * Gets the items in the order
	 * @return The menu item array
	 */
	public MenuItem[] getOrder(){
		return this.order;
	}

	/**
	 * Sets the order
	 * @param order the order to be set
	 */
	public void setOrder(MenuItem[] order){
		this.order=order;
	}

	/**
	 * sets the price of the order
	 * @param price the price of the order
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * gets the number of items in the order
	 * @return the number of items in the order
	 */
	public int getNumOfItems(){
		return this.numofItems;
	}

	/**
	 * sets the order number
	 * @param orderNumber the order number to be set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
