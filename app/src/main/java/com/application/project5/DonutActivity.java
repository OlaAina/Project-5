package com.application.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonutActivity extends AppCompatActivity {
    private TextView subtotal, quantityField;
    private Button orderButton;

    Order order = OrderDetailActivity.getOrder();
    Donuts donut = new Donuts();
    String flavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        ListView donutList= findViewById(R.id.donutList);
        subtotal = findViewById(R.id.subtotal);
        orderButton = findViewById(R.id.addToOrder);
        quantityField = findViewById(R.id.quantityField);

        String[] flavors = {"Blueberry", "Pumpkin Spice", "Powdered", "Glazed", "Frosted", "Jelly", "Birthday Cake"};
        orderButton.setEnabled(false);
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1, flavors);
        donutList.setAdapter(arrayAdapter);
        donutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            flavor = donutList.getItemAtPosition(position).toString();
            donut.itemPrice();
            subtotal.setText("$" + String.format("%.2f", donut.getItemPrice()));
            quantityField.setEnabled(true);
            }
        });
    }

    /**
     * Method to check if quantity is valid
     * @return check if the quantity entered is an integer
     * -1 otherwise
     */
    public int checkQuantity() {
        try {
            int check = Integer.parseInt(quantityField.getText().toString());
            return check;
        }
        catch (NumberFormatException| NullPointerException  e) {
            orderButton.setEnabled(false);
            Toast.makeText(this, "Please enter a nonzero numerical integer",
                    Toast.LENGTH_LONG).show();
            return -1;
        }
    }

    /**
     * Method to adjust sub-total after entering the quantity
     */
    public void subtotal(View v) {
        int check = checkQuantity();
        if (check != -1) {
            donut.setQuantity(check);
            if(check >= 1) {
                orderButton.setEnabled(true);
                subtotal.setText("");
                donut.setFlavor(flavor);
                donut.totalPrice();
                double total = donut.getTotalPrice();
                donut.setItemPrice(total);
                subtotal.setText("$" + String.format("%.2f" , total));
            }
            else if(check == 0 || donut.getTotalPrice() == 0) {
                Toast.makeText(this, "Please enter nonzero quantity",
                        Toast.LENGTH_LONG).show();
                subtotal.setText("");
                subtotal.setText("$0.00");
                orderButton.setEnabled(false);
            }
            else {
                Toast.makeText(this, "Please enter nonzero integer",
                        Toast.LENGTH_LONG).show();
                orderButton.setEnabled(false);
            }
        }
        else {
            subtotal.setText("");
            orderButton.setEnabled(false);
        }
    }
    /**
     * Method to add doughnut to order
     * @param v, on click will add doughnut to order
     */
    public void addToOrder(View v) {
        if(donut.getTotalPrice() == 0 ) {
            Toast.makeText(this, "Please enter quantity",
                    Toast.LENGTH_LONG).show();
            orderButton.setEnabled(false);
            return;
        }
        if(!donut.equals(null)) {
            order.add(donut);
            Toast.makeText(this,  donut + " added",
                    Toast.LENGTH_LONG).show();
            clear();
            donut = new Donuts();
        }
    }

    /**
     * Method to clear doughnut selections after adding to order
     */
    void clear() {
        subtotal.setText("");
        subtotal.setText("$0.00");
        quantityField.setText("");
        quantityField.setEnabled(false);
        orderButton.setEnabled(false);
    }

}