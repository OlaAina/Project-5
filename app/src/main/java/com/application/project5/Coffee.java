package com.application.project5;
/**
 * Class that describes object coffee, extends class MenuItem
 * This class consists of:
 * A method to calculate and set price
 * A method to check if equal
 * A method to display string representation of object coffee
 * Getter and setter methods
 * Two methods implemented from class Customizable
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class Coffee extends MenuItem implements Customizable{

	String size;
	String addins;
	int numOfAddins;


	final static String SHORT = "Short";
	final static Double SHORT_PRICE = 1.99;
	final static String TALL = "Tall";
	final static Double TALL_PRICE = 2.49;
	final static String GRANDE = "Grande";
	final static Double GRANDE_PRICE = 2.99;
	final static Double VENTI_PRICE = 3.49;
	final static Double ADDIN_COST = .20;
	static int tracker = 0;


	/**
	 * Method to set the price of a coffee depending on size and number of add-ins
	 */
	@Override
	public void itemPrice() {

		if(size.equals(SHORT)) {
			setItemPrice(SHORT_PRICE + (numOfAddins * ADDIN_COST));

		}

		else if(size.equals(TALL)) {
			setItemPrice(TALL_PRICE + (numOfAddins * ADDIN_COST));

		}

		else if(size.equals(GRANDE)) {
			setItemPrice(GRANDE_PRICE + (numOfAddins * ADDIN_COST));

		}

		else {
			setItemPrice(VENTI_PRICE + (numOfAddins * ADDIN_COST));
		}

	}

	/**
	 * Method to check if two coffees are equal
	 * @param obj, an object to compare
	 * @return true if equal, otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coffee)
		{
			Coffee coffee= (Coffee) obj;
			if(coffee.size.equals(this.size)) {
				if(coffee.getItemPrice() == this.getItemPrice()) {
					if(coffee.addins.equals(this.addins)) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	 * Method to display string representation of coffee
	 * @return string representation of coffee
	 */
	@Override
	public String toString() {
		return R.string.coffee_size + size + R.string.seperator + R.string.addins_string + addins + R.string.seperator + R.string.quantity_string + this.getQuantity();

	}

	/**
	 * Method to get the size of coffee
	 * @return size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Method to set size of coffee
	 * @param size, the string size of the coffee
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Method to get the string of add-ins
	 * @return addins
	 */
	public String getAddins() {
		return addins;
	}

	/**
	 * Set the string of add-ins
	 * @param addins, the string of addins
	 */
	public void setAddins(String addins) {
		this.addins = addins;
	}


	/**
	 * get the number of Addins
	 * @return numOfAddins
	 */
	public int getNumOfAddins() {
		return numOfAddins;
	}

	/**
	 * Set the number of addins
	 * @param numOfAddins, the numerical number of addins
	 */
	public void setNumOfAddins(int numOfAddins) {
		this.numOfAddins = numOfAddins;
	}

	/**
	 * Method to add addins
	 * @param obj, object to be added to string of addins
	 * @return true, if added false otherwise
	 */
	@Override
	public boolean add(Object obj) {
		String add = (String) obj;
		if(this.addins == null){
			this.addins = (add + ", ");
			tracker++;
			return true;
		}
		if(this.addins.contains(add)){
			return false;
		}
		else {
			this.addins = this.addins.concat((add + ", "));
			tracker++;
			setNumOfAddins(tracker);
			return true;
		}
	}

	/**
	 * Method to remove addins
	 * @param obj, object to be remove to string of addins
	 * @return true, if remove false otherwise
	 */
	@Override
	public boolean remove(Object obj) {
		String remove = (String) obj;
		if(this.addins.contains(remove)){
			this.addins = this.addins.replace((remove + ", "), "");
			tracker--;
			setNumOfAddins(tracker);
			return true;
		}
		else {
			return false;
		}
	}
}
