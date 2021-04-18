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


public class OrderDetailActivity extends AppCompatActivity {

    private ListView itemsListView;
    private TextView subtotal;
    private TextView tax;
    private TextView finalPrice;

    StoreOrders storeOrders= StoreOrdersActivity.getStoreOrders();
    ArrayList<MenuItem> arrayList=new ArrayList<>();
    ArrayAdapter arrayAdapter=null;

    static Order order= new Order();
    final static double TAX_RATE= 6.625/100;

    MenuItem selected= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setListView();
        setSubTotal();
        setTax();
        setFinalPrice();
    }

    public void setListView(){
        itemsListView=(ListView)findViewById(R.id.itemsListView);
        MenuItem[] indexes= new MenuItem[order.getNumOfItems()];

        for (int i = 0; i < order.getOrder().length; i++) {
            if (order.getOrder()[i] != null) {
                indexes[i] = order.getOrder()[i];
                arrayList.add(indexes[i]);
            }
        }
        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        itemsListView.setAdapter(arrayAdapter);
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selected= (MenuItem) itemsListView.getItemAtPosition(position);
            }
        });
    }

    public void setSubTotal(){
        subtotal= findViewById(R.id.subTotal);
        subtotal.setText("");
        double orderPrice=order.getOrderPrice();
        if(orderPrice<0){
            subtotal.setText(String.format("$"+"%.2f", orderPrice*(-1)));
        }
        else {
            subtotal.setText(String.format("$" + "%.2f", orderPrice));
        }

    }

    public void setTax(){
        tax= findViewById(R.id.tax);
        tax.setText("");
        double price= order.getOrderPrice();
        double taxPrice= (TAX_RATE* price);
        if(taxPrice<0){
            tax.setText(String.format("$"+"%.2f",taxPrice*(-1)));
        }
        else {
            tax.setText(String.format("$" + "%.2f", taxPrice));
        }
    }

    public void setFinalPrice(){
        finalPrice= findViewById(R.id.finalPrice);
        finalPrice.setText("");
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
    public void removeItem(View view){
        try {
            order.setPrice(order.getOrderPrice() - selected.getItemPrice());
            order.remove(selected);
            setListView();
            setSubTotal();
            setTax();
            setFinalPrice();
        }
        catch(NullPointerException ex){
            Toast.makeText(this, "Null", Toast.LENGTH_LONG);
        }
    }
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
                Toast.makeText(this, "Null", Toast.LENGTH_LONG);
            }
            arrayList.clear();
            arrayAdapter.notifyDataSetChanged();
            order = new Order();
            MenuItem[] itemList = new MenuItem[4];
            order.setOrder(itemList);
            setFinalPrice();
            setTax();
            setSubTotal();
            Toast.makeText(this, "Order Placed", Toast.LENGTH_LONG);
        }
        else{
            Toast.makeText(this, "Order Empty", Toast.LENGTH_LONG);
        }
    }

    public ListView getItemsListView() {
        return itemsListView;
    }

    public void setItemsListView(ListView itemsListView) {
        this.itemsListView = itemsListView;
    }

    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
    }

    public static Order getOrder() {
        return order;
    }

    public static void setOrder(Order order) {
        OrderDetailActivity.order = order;
    }
    private static int startingNumber=1;
    public static void makeOrderNumber(){
        order.setOrderNumber(startingNumber);
        startingNumber++;
    }

}