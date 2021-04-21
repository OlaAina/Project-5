package com.application.project5;

import java.io.FileWriter;
import java.io.IOException;
/**
 * This class defines the store orders object
 * The class consists of:
 * A method to find an Order
 * A method to grow the Order list
 * A method to add and remove an order
 * An export method
 * getters and setters
 * @author Adeola Adebanjo, Olaolu Aina
 */
public class StoreOrders implements Customizable {

	private Order[] orderList; 
	private int numofOrders;

	static final int COULDNT_FIND=-1;

	/**
	 *Helper find method
	 * @param obj the order to be found
	 * @return the index in which the order was found
	 */
	private int find(Object obj){
		Order order=(Order) obj;
		int empty=0;
		for(int i=0;i<orderList.length;i++) {
			if(orderList[i]!=null) {
				empty++;
			}
		}
		if(empty==0) {
			return COULDNT_FIND;
		}


		for(int i=0;i<orderList.length-1;i++) {
			if(orderList[i]!=null) {
				if(order.getOrderNumber()==orderList[i].getOrderNumber())
				{
					return i;
				}
			}
		}
		return COULDNT_FIND;
	}

	/**
	 *Add method to add order
	 * @param obj the order to be added
	 * @return if the order was truly added
	 */
	@Override
	public boolean add(Object obj) {
		Order order= (Order) obj;
		int alreadyAdded=this.find(order);
		if(alreadyAdded==-1) {
			int foundSpace=0;
			for(int i=0;i<orderList.length;i++)
			{
				if(orderList[i]==null)
				{
					orderList[i]=order;
					foundSpace=1;
					break;
				}
			}
			if(foundSpace==0) {
				this.grow();
				orderList[numofOrders]=order;
			}
			this.numofOrders++;
			return true;
		}
		return false;
	}

	/**
	 * Remove method for order
	 * @param obj the order to be removed
	 * @return If the order was truly removed
	 */
	@Override
	public boolean remove(Object obj) {
		int removeThis;
		Order order= (Order) obj;
		removeThis=this.find(order);

		if(removeThis == -1) {
			return false;
		}
		else {
			this.orderList[removeThis] = null;
			for(int i = removeThis; i < orderList.length-1; i++)
			{
				orderList[i]=orderList[i+1];

			}
			orderList[numofOrders-1] = null;
			this.numofOrders--;
			return true;
		}
	}
	/**
	 * This method increases a full array by the size of 4
	 */
	static final int GROWTH=4;
	private void grow() {
		int currentSpace= this.orderList.length;
		Order[] extraSpace= new Order[currentSpace+GROWTH];
		for (int i=0;i<this.orderList.length;i++) {
			extraSpace[i]=this.orderList[i];
		}
		this.orderList= extraSpace;
	}

	/**
	 * Gets the orderList array
	 * @return the array of orders
	 */
	public Order[] getOrderList() {
		return orderList;
	}

	/**
	 * Sets the orderList of the storeOrders
	 * @param orderList the orderList to be set
	 */
	public void setOrderList(Order[] orderList){
		this.orderList= orderList;
	}
}
