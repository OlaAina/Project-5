package com.application.project5;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CoffeeActivity extends AppCompatActivity {

    private TextView subtotalText, quantity;

    private CheckBox creamBox, syrupBox, milkBox, caramelBox, whipBox;
    private RadioGroup sizes;
    private Button orderButton;
    Order order= OrderDetailActivity.getOrder();
    Coffee coffee = new Coffee();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        subtotalText = findViewById(R.id.subtotalText);
        quantity = findViewById(R.id.quantity);
        creamBox = findViewById(R.id.creamBox);
        syrupBox = findViewById(R.id.creamBox);
        milkBox = findViewById(R.id.milkBox);
        whipBox = findViewById(R.id.whipBox);
        caramelBox = findViewById(R.id.caramelBox);
        sizes = findViewById(R.id.sizes);
        orderButton = findViewById(R.id.orderButton);

    }

    String getAddins() {
        String addins = "";
        if(creamBox.isChecked()) {
            addins = addins.concat(creamBox.getText().toString() + ", ");
        }
        if(syrupBox.isChecked()) {
            addins = addins.concat(syrupBox.getText().toString() + ", ");
        }
        if(milkBox.isChecked()) {
            addins = addins.concat(milkBox.getText().toString() + ", ");
        }
        if(caramelBox.isChecked()) {
            addins = addins.concat(caramelBox.getText().toString()+ ", ");
        }
        if(whipBox.isChecked()) {
            addins = addins.concat(whipBox.getText().toString() + " ");
        }
        if(addins.equals("")) {
            addins = "none";
            return addins;
        }
        else {
            coffee.setAddins(addins);
            return addins;
        }
    }

    boolean checkAllCheckboxes() {
        if(!((((creamBox.isChecked() && syrupBox.isChecked()) && milkBox.isChecked())
                && caramelBox.isChecked()) && whipBox.isChecked())){
            return true;
        }
        return false;
    }

    void countHelper() {
        coffee.numOfAddins++;
        coffee.setNumOfAddins(coffee.getNumOfAddins());
        coffee.itemPrice();
        subtotalText.setText("");
        subtotalText.setText("$" + (String.format("%.2f", coffee.getItemPrice())));
    }

    public int countAddins(View v) {
        if(getCoffeeSize() != null) {
            coffee.setSize(getCoffeeSize());
            int addins = 0;
            coffee.numOfAddins = 0;
            if(creamBox.isChecked()) {
                countHelper();
                addins++;
            }
            if(syrupBox.isChecked()) {
                countHelper();
                addins++;
            }
            if(milkBox.isChecked()) {
                countHelper();
                addins++;
            }
            if(caramelBox.isChecked()) {
                countHelper();
                addins++;
            }
            if(whipBox.isChecked()) {
                countHelper();
                addins++;
            }

            if(checkAllCheckboxes()) {
                coffee.setNumOfAddins(coffee.getNumOfAddins());
                coffee.itemPrice();
                subtotalText.setText("");
                subtotalText.setText("$" + (String.format("%.2f", coffee.getItemPrice())));
            }
            return addins;
        }
        clearBoxes();
        return 0;
    }

    public String getCoffeeSize() {
        try {
            int id = sizes.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(id);
            String size = radioButton.getText().toString();
            return size;

        }
        catch(NullPointerException e) {
            Toast.makeText(this, "Please select coffee size",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }


    public void getInitialPrice(View v) {
        if(getCoffeeSize() != null) {
            coffee.setSize(getCoffeeSize());
            coffee.itemPrice();
            subtotalText.setText("");
            subtotalText.setText("$" + (String.format("%.2f", coffee.getItemPrice())));
            enableBoxes();
            quantity.setEnabled(true);
            quantity.setText("");
            coffee.setQuantity(0);
            coffee.totalPrice();
        }
    }


    public int checkQuantity() {
        try {
            int check = Integer.parseInt(quantity.getText().toString());
            return check;
        }
        catch (NumberFormatException| NullPointerException  e) {
            orderButton.setEnabled(false);
            Toast.makeText(this, "Invalid integer!",
                    Toast.LENGTH_LONG).show();
            return -1;
        }
    }

    public void updateSubtotal(View v) {
        int check = checkQuantity();
        if(check != -1) {
            if(check >= 1) {
                coffee.setQuantity(check);
                if(getCoffeeSize() != null) {
                    coffee.itemPrice();
                    orderButton.setEnabled(true);
                    subtotalText.setText("");
                    coffee.totalPrice();
                    double total = coffee.getTotalPrice();
                    coffee.setItemPrice(total);
                    subtotalText.setText("$" + String.format("%.2f" , total));
                }
            }
            else if(check == 0) {
                Toast.makeText(this, "Please enter nonzero integer",
                        Toast.LENGTH_LONG).show();
                subtotalText.setText("");
                subtotalText.setText("$0.00");
                orderButton.setEnabled(false);
            }
            else {
                Toast.makeText(this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                subtotalText.setText("");
                orderButton.setEnabled(false);

            }
        }
    }

    public void addToOrder(View v) {
        if(coffee.getTotalPrice() == 0 ) {
            orderButton.setEnabled(false);
            Toast.makeText(this, "error adding coffee to order",
                    Toast.LENGTH_LONG).show();
        }

        if(!coffee.equals(null)) {
            coffee.setAddins(getAddins());
            order.add(coffee);
            coffee = new Coffee();
            clear();
            Toast.makeText(this,  coffee + "added",
                    Toast.LENGTH_LONG).show();
        }
    }

    void clear() {
        coffee.setNumOfAddins(0);
        clearBoxes();
        quantity.setText("");
        quantity.setEnabled(false);
        subtotalText.setText("");
        RadioButton selected = (RadioButton)findViewById(sizes.getCheckedRadioButtonId());
        selected.setSelected(false);
        orderButton.setEnabled(false);
        disableBoxes();
    }

    void clearBoxes() {
        creamBox.setSelected(false);
        syrupBox.setSelected(false);
        milkBox.setSelected(false);
        caramelBox.setSelected(false);
        whipBox.setSelected(false);
    }

    void disableBoxes() {
        creamBox.setEnabled(false);
        syrupBox.setEnabled(false);
        milkBox.setEnabled(false);
        caramelBox.setEnabled(false);
        whipBox.setEnabled(false);
    }

    void enableBoxes() {
        creamBox.setEnabled(true);
        syrupBox.setEnabled(true);
        milkBox.setEnabled(true);
        caramelBox.setEnabled(true);
        whipBox.setEnabled(true);
    }



}