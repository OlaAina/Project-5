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
/**
 * Class that controls the Donuts GUI
 * This class consists of:
 * An on create method
 * A method to check quantity
 * A method to display the sub-total
 * A method to add to order
 * A method to clear all doughnut selections
 *
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
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

        String[] flavors = {getString(R.string.blueberry), getString(R.string.pumpSpice), getString(R.string.powder), getString(R.string.glaze), getString(R.string.frost), getString(R.string.jelly), getString(R.string.Bdaycake)};
        orderButton.setEnabled(false);
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1, flavors);
        donutList.setAdapter(arrayAdapter);
        donutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            flavor = donutList.getItemAtPosition(position).toString();
            donut.itemPrice();
            subtotal.setText(getString(R.string.dollar) + String.format("%.2f", donut.getItemPrice()));
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
            Toast.makeText(this, R.string.nonzero_int,
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
                subtotal.setText(getString(R.string.reset));
                donut.setFlavor(flavor);
                donut.totalPrice();
                double total = donut.getTotalPrice();
                donut.setItemPrice(total);
                subtotal.setText(getString(R.string.dollar) + String.format("%.2f" , total));
            }
            else if(check == 0 || donut.getTotalPrice() == 0) {
                Toast.makeText(this, R.string.enter_quantity,
                        Toast.LENGTH_LONG).show();
                subtotal.setText(getString(R.string.reset));
                subtotal.setText(getString(R.string.nothing_selected));
                orderButton.setEnabled(false);
            }
            /* else {
                Toast.makeText(this, "Please enter nonzero integer",
                        Toast.LENGTH_LONG).show();
                orderButton.setEnabled(false);
            }*/
        }
        else {
            subtotal.setText(getString(R.string.reset));
            orderButton.setEnabled(false);
        }
    }
    /**
     * Method to add doughnut to order
     * @param v, on click will add doughnut to order
     */
    public void addToOrder(View v) {
        if(donut.getTotalPrice() == 0 ) {
            Toast.makeText(this, getString(R.string.enter_quantity),
                    Toast.LENGTH_LONG).show();
            orderButton.setEnabled(false);
            return;
        }
        if(!donut.equals(null)) {
            order.add(donut);
            Toast.makeText(this,  donut + getString(R.string.added),
                    Toast.LENGTH_LONG).show();
            clear();
            donut = new Donuts();
        }
    }

    /**
     * Method to clear doughnut selections after adding to order
     */
    void clear() {
        subtotal.setText(getString(R.string.reset));
        subtotal.setText(getString(R.string.nothing_selected));
        quantityField.setText(getString(R.string.reset));
        quantityField.setEnabled(false);
        orderButton.setEnabled(false);
    }

}