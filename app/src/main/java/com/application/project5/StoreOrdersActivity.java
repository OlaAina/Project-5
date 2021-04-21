package com.application.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static StoreOrders storeOrders = new StoreOrders();
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    final static double TAX_RATE = 6.625 / 100;

    private Spinner spinner;
    private ListView itemsListView;
    private TextView subtotal;
    private TextView tax;
    private TextView finalPrice;

    private int selected;
    ArrayList<MenuItem> arrayList=new ArrayList<>();
    ArrayAdapter arrayAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        setSpinner();
    }

    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public static void setStoreOrders(StoreOrders storeOrders) {
        StoreOrdersActivity.storeOrders = storeOrders;
    }

    public void setSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        Integer[] indexes= new Integer[storeOrders.getOrderList().length];

        List<Integer> orderNumbers = new ArrayList<Integer>();

        for(int i=0; i<storeOrders.getOrderList().length;i++){
            if(storeOrders.getOrderList()[i]!=null) {
                indexes[i] = storeOrders.getOrderList()[i].getOrderNumber();
                orderNumbers.add(indexes[i]);
            }
        }

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, orderNumbers);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = (int) parent.getItemAtPosition(position);
        display(selected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /**
     * populates the order price text field
     * @param orderIndex the order whose price is to be displayed
     */
    public void setOrderPrice(int orderIndex){
        subtotal=findViewById(R.id.subTotal);
        subtotal.setText(R.string.nothing_selected);
        double orderPrice=storeOrders.getOrderList()[orderIndex].getOrderPrice();
        if(orderPrice<0){
            this.subtotal.setText(String.format("$" + "%.2f", orderPrice*(-1)));
        }
        else {
            this.subtotal.setText(String.format("$" + "%.2f", orderPrice));
        }
    }

    /**
     * Sets the tax field
     * @param orderIndex the order index whose tax is to be displayed
     */
    public void setTax(int orderIndex){
        tax=findViewById(R.id.tax);
        tax.setText(R.string.nothing_selected);
        double orderPrice= storeOrders.getOrderList()[orderIndex].getOrderPrice();
        double taxPrice= TAX_RATE*orderPrice;
        if(taxPrice<0){
            tax.setText(String.format("$"+"%.2f",taxPrice*(-1)));
        }
        else {
            tax.setText(String.format("$" + "%.2f", taxPrice));
        }
    }
    /**
     * Sets the final price field
     * @param orderIndex the order index whose final price is to be displayed
     */
    public void setFinalPrice(int orderIndex){
        finalPrice=findViewById(R.id.finalPrice);
        finalPrice.setText(R.string.nothing_selected);
        double finalPrice;
        double orderPrice= storeOrders.getOrderList()[orderIndex].getOrderPrice();
        double taxPrice= TAX_RATE*orderPrice;
        finalPrice= taxPrice+ orderPrice;
        if(finalPrice<0){
            this.finalPrice.setText("$"+String.format("%.2f",finalPrice*(-1)));
        }
        this.finalPrice.setText("$"+String.format("%.2f",finalPrice));
    }

    public void display(int selected) {
        itemsListView = (ListView) findViewById(R.id.orderTextArea);
        int order = selected;
        int select = 0;
        arrayList = new ArrayList<>();
        if (selected != 0) {
            for (int x = 0; x < storeOrders.getOrderList().length; x++) {
                if (storeOrders.getOrderList()[x] != null) {
                    if (order == storeOrders.getOrderList()[x].getOrderNumber()) {
                        select = x;
                    }
                }
            }

            Order display = storeOrders.getOrderList()[select];
            for (int i = 0; i < display.getOrder().length; i++) {
                if (display.getOrder()[i] != null) {
                    arrayList.add(display.getOrder()[i]);
                }
            }

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            itemsListView.setAdapter(arrayAdapter);

            setOrderPrice(select);
            setTax(select);
            setFinalPrice(select);
        } else {
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            itemsListView.setAdapter(arrayAdapter);
        }
    }


    /**
     * Cancel an order
     * @param view click of the cancel order button
     */
    public void cancelOrder(View view){
        try {
            int orderNumber= selected ;
            int select=0;
            for(int i=0;i<storeOrders.getOrderList().length;i++){
                if(storeOrders.getOrderList()[i]!=null) {
                    if (storeOrders.getOrderList()[i].getOrderNumber() == orderNumber) {
                        select = i;
                    }
                }
            }
            storeOrders.remove(storeOrders.getOrderList()[select]);
            setSpinner();
            subtotal.setText(R.string.nothing_selected);
            tax.setText(R.string.nothing_selected);
            finalPrice.setText(R.string.nothing_selected);
            arrayList.clear();
            display(0);
            Toast.makeText(this, R.string.cancel_success , Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException ex){
            Toast.makeText(this, R.string.cancel_failed , Toast.LENGTH_LONG).show();
        }
    }
}
