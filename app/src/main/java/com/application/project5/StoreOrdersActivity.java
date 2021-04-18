package com.application.project5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class StoreOrdersActivity extends AppCompatActivity {

    private static StoreOrders storeOrders= new StoreOrders();
    private static DecimalFormat df2= new DecimalFormat("#.##");
    final static double TAX_RATE= 6.625/100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);


    }
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public static void setStoreOrders(StoreOrders storeOrders) {
        StoreOrdersActivity.storeOrders = storeOrders;
    }
}