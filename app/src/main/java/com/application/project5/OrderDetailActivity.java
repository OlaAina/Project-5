package com.application.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that controls the Order Details Activity
 * This class consists of:
 * Methods to get string representations of coffee add-ins
 * Methods to Place order and remove items
 * Methods to set all fields and list view
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class OrderDetailActivity extends AppCompatActivity {

    private ListView itemsListView;
    private TextView subtotal;
    private TextView tax;
    private TextView finalPrice;

    StoreOrders storeOrders= StoreOrdersActivity.getStoreOrders();
    ArrayAdapter arrayAdapter=null;
    ArrayList<MenuItem> arrayList=new ArrayList<>();

    static Order order= new Order();
    final static double TAX_RATE= 6.625/100;

    MenuItem selected= null;

    /**
     * Defines what happens on create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setListView();
        setSubTotal();
        setTax();
        setFinalPrice();
    }

    /**
     * set the list view
     */
    public void setListView(){

        itemsListView=(ListView)findViewById(R.id.itemsListView);
        MenuItem[] indexes= new MenuItem[order.getNumOfItems()];
        arrayList.clear();
        for (int i = 0; i < order.getOrder().length; i++) {
            if (order.getOrder()[i] != null) {
                indexes[i] = order.getOrder()[i];
                arrayList.add(indexes[i]);
            }
        }
        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);
        itemsListView.setAdapter(arrayAdapter);
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selected= (MenuItem) itemsListView.getItemAtPosition(position);
            }
        });
    }

    /**
     * Set the subtotal
     */
    public void setSubTotal(){
        subtotal= findViewById(R.id.subTotal);
        subtotal.setText(R.string.empty);
        double orderPrice=order.getOrderPrice();
        if(orderPrice<0){
            subtotal.setText(String.format("$"+"%.2f", orderPrice*(-1)));
        }
        else {
            subtotal.setText(String.format("$" + "%.2f", orderPrice));
        }

    }

    /**
     * set the tax field
     */
    public void setTax(){
        tax= findViewById(R.id.tax);
        tax.setText(R.string.empty);
        double price= order.getOrderPrice();
        double taxPrice= (TAX_RATE* price);
        if(taxPrice<0){
            tax.setText(String.format("$"+"%.2f",taxPrice*(-1)));
        }
        else {
            tax.setText(String.format("$" + "%.2f", taxPrice));
        }
    }

    /**
     * set the final price field
     */
    public void setFinalPrice(){
        finalPrice= findViewById(R.id.finalPrice);
        finalPrice.setText(R.string.empty);
        double finalPrice;
        double orderPrice= order.getOrderPrice();
        double taxPrice= TAX_RATE*orderPrice;
        finalPrice= taxPrice+ orderPrice;
        if(finalPrice<0){
            this.finalPrice.setText("$"+String.format("%.2f",finalPrice*(-1)));
        }
        else {
            this.finalPrice.setText("$" + String.format("%.2f", finalPrice));
        }
    }

    /**
     * remove a selected item
     * @param view the click of the remove item button
     */
    public void removeItem(View view){
        try {
                double priceToRemove= selected.getItemPrice();
                boolean removed= order.remove(selected);
                if(removed) {
                    order.setPrice(order.getOrderPrice() - priceToRemove);
                    setListView();
                    setSubTotal();
                    setTax();
                    setFinalPrice();
                }
                else{
                    Toast.makeText(this, R.string.item_not_selected, Toast.LENGTH_LONG).show();
                }
        }
        catch(NullPointerException ex){
            Toast.makeText(this, R.string.remove_failed, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Place an order
     * @param view the click of the place order button
     */
    public void placeOrder(View view){
        int empty=0;
        for(int i=0; i< order.getNumOfItems();i++){
            if(order.getOrder()[i]!=null){
                empty=1;
            }
        }
        if(empty==1) {
            try {
                makeOrderNumber();
                storeOrders.add(order);
            } catch (NullPointerException ex) {
                Toast.makeText(this, R.string.error_in_placing_order, Toast.LENGTH_LONG).show();
            }
            arrayList.clear();
            arrayAdapter.notifyDataSetChanged();
            order = new Order();
            MenuItem[] itemList = new MenuItem[4];
            order.setOrder(itemList);
            setFinalPrice();
            setTax();
            setSubTotal();
            Toast.makeText(this, R.string.place_order_success, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, R.string.empty_order, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * getter for order
     * @return the order
     */
    public static Order getOrder() {
        return order;
    }

    private static int startingNumber=1;

    /**
     * creates the order number
     */
    public static void makeOrderNumber(){
        order.setOrderNumber(startingNumber);
        startingNumber++;
    }

}