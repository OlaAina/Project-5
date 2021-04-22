package com.application.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/**
 * Class that controls the Coffee Activity
 * This class consists of:
 * Methods to get string representations of coffee add-ins
 * Methods to add and remove coffee addins
 * Method to check if all boxes are not selected
 * A helper method for method addAddins
 * Methods to enable, disable and clear check boxes
 * Method to get the base price of initial price
 * Method to get coffee size
 * Method to check the if the quantity is valid
 * Method to update subtotal
 * Method to add item to order
 * Method to clear all selections
 *
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class CoffeeActivity extends AppCompatActivity {

    private TextView subtotalText, quantity;

    private CheckBox creamBox, syrupBox, milkBox, caramelBox, whipBox;
    private RadioGroup sizes;
    private Button orderButton;
    Order order = OrderDetailActivity.getOrder();
    Coffee coffee = new Coffee();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        subtotalText = findViewById(R.id.subtotalText);
        quantity = findViewById(R.id.quantity);
        creamBox = findViewById(R.id.creamBox);
        syrupBox = findViewById(R.id.syrupBox);
        milkBox = findViewById(R.id.milkBox);
        whipBox = findViewById(R.id.whipBox);
        caramelBox = findViewById(R.id.caramelBox);
        sizes = findViewById(R.id.sizes);
        orderButton = findViewById(R.id.orderButton);
        orderButton.setEnabled(false);

    }


    /**
    * Method to get a string representation of all add-ins
    * @return addins, the string of add-ins
    */
    String getAddins() {
        String addins = "";
        if(creamBox.isChecked()) {
            addins = addins.concat(creamBox.getText().toString() + getString(R.string.comma));
        }
        if(syrupBox.isChecked()) {
            addins = addins.concat(syrupBox.getText().toString() + getString(R.string.comma));
        }
        if(milkBox.isChecked()) {
            addins = addins.concat(milkBox.getText().toString() + getString(R.string.comma));
        }
        if(caramelBox.isChecked()) {
            addins = addins.concat(caramelBox.getText().toString()+ getString(R.string.comma));
        }
        if(whipBox.isChecked()) {
            addins = addins.concat(whipBox.getText().toString() + getString(R.string.space));
        }
        if(addins.equals("")) {
            addins = getString(R.string.none);
            return addins;
        }
        else {
            coffee.setAddins(addins);
            return addins;
        }
    }

    /**
    * Method to check if all boxes are selected
    * @return true if all are not selected, false otherwise
    */
    boolean checkAllCheckboxes() {
        if(!((((creamBox.isChecked() && syrupBox.isChecked()) && milkBox.isChecked())
                && caramelBox.isChecked()) && whipBox.isChecked())){
            return true;
        }
        return false;
    }
    
     /**
     * Helper method for countAddins
     */
    void countHelper() {
        coffee.itemPrice();
        quantity.setText(getString(R.string.reset));
        coffee.setQuantity(0);
        coffee.totalPrice();
        subtotalText.setText(R.string.reset);
        subtotalText.setText(getString(R.string.dollar) + (String.format("%.2f", coffee.getItemPrice())));
    }

    /**
     * Method to remove addins and update subtotal
     */

    void removeAddins(){
        if(!creamBox.isChecked()) {
            coffee.remove(" " + creamBox.getText().toString());
            countHelper();
        }
        if(!syrupBox.isChecked()) {
            coffee.remove(syrupBox.getText().toString());
            countHelper();
        }
        if(!milkBox.isChecked()) {
            coffee.remove(milkBox.getText().toString());
            countHelper();
        }
        if(!caramelBox.isChecked()) {
            coffee.remove(caramelBox.getText().toString());
            countHelper();
        }
        if(!whipBox.isChecked()) {
            String whip = whipBox.getText().toString();
            whip = whip.replace(" ", "");
            coffee.remove(whip);
            countHelper();
        }
    }

    /**
    * Method to add add-ins and update sub-total
    * @return addins, the number of add-ins
    */
    public void addAddins(View v) {
        if(getCoffeeSize() != null) {
            coffee.setSize(getCoffeeSize());
            coffee.numOfAddins = 0;
            if(creamBox.isChecked()) {
                coffee.add( " " + creamBox.getText().toString());
                countHelper();
            }
            if(syrupBox.isChecked()) {
                coffee.add(syrupBox.getText().toString());
                countHelper();
            }
            if(milkBox.isChecked()) {
                coffee.add(milkBox.getText().toString());
                countHelper();
            }
            if(caramelBox.isChecked()) {
                coffee.add(caramelBox.getText().toString());
                countHelper();
            }
            if(whipBox.isChecked()) {
                String whip = whipBox.getText().toString();
                whip = whip.replace(" ", "");
                coffee.add(whip);
                countHelper();
            }

            if(checkAllCheckboxes()) {
                coffee.setNumOfAddins(coffee.getNumOfAddins());
                countHelper();
            }
            removeAddins();
        }
        else {
            clearBoxes();
        }
    }


    /**
    * Method to get the coffee size of from the radio buttons
    * @return size, coffee size
    */
    public String getCoffeeSize() {
        try {
            int id = sizes.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(id);
            String size = radioButton.getText().toString();
            return size;

        }
        catch(NullPointerException e) {
            Toast.makeText(this, R.string.select_size,
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    
    /**
    * Method to display base price of coffee depending on radio buttons
    */
    public void getInitialPrice(View v) {
        if(getCoffeeSize() != null) {
            coffee.setSize(getCoffeeSize());
            coffee.itemPrice();
            subtotalText.setText(R.string.reset);
            subtotalText.setText(getString(R.string.dollar) + (String.format("%.2f", coffee.getItemPrice())));
            enableBoxes();
            quantity.setEnabled(true);
            quantity.setText(getString(R.string.reset));
            coffee.setQuantity(0);
            coffee.totalPrice();
        }
    }

    /**
     * Method that checks the quantity
     * return check, the quantity or -1 if invalid
     */
    public int checkQuantity() {
        try {
            int check = Integer.parseInt(quantity.getText().toString());
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
    * Update sub-total with quantity inputed
    * @param v, on enter will update sub-total
    */
    public void updateSubtotal(View v) {
        int check = checkQuantity();
        if(check != -1) {
            if(check >= 1) {
                coffee.setQuantity(check);
                if(getCoffeeSize() != null) {
                    coffee.itemPrice();
                    orderButton.setEnabled(true);
                    subtotalText.setText(getString(R.string.reset));
                    coffee.totalPrice();
                    double total = coffee.getTotalPrice();
                    coffee.setItemPrice(total);
                    subtotalText.setText(getString(R.string.dollar) + String.format("%.2f" , total));
                }
            }
            else if(check == 0) {
                Toast.makeText(this, R.string.nonzero_int,
                        Toast.LENGTH_LONG).show();
                subtotalText.setText(getString(R.string.reset));
                subtotalText.setText(getString(R.string.nothing_selected));
                orderButton.setEnabled(false);
            }
        }
    }


     /**
     * Method to add coffee to order
     * @param v, on click will add to order
     */
    public void addToOrder(View v) {
        if(coffee.getTotalPrice() == 0 ) {
            orderButton.setEnabled(false);
            Toast.makeText(this, R.string.enter_quantity,
                    Toast.LENGTH_LONG).show();
        }

       else if(!coffee.equals(null)) {
           coffee.setAddins(getAddins());
            order.add(coffee);
            Toast.makeText(this,  coffee + getString(R.string.added),
                    Toast.LENGTH_LONG).show();
            coffee = new Coffee();
            coffee.tracker = 0;
            clear();
        }
    }

    /**
     * Method to clear a add-ins boxes
     */
    void clear() {
        coffee.setNumOfAddins(0);
        clearBoxes();
        quantity.setText(getString(R.string.reset));
        quantity.setEnabled(false);
        subtotalText.setText(getString(R.string.reset));
        subtotalText.setText(getString(R.string.nothing_selected));
        RadioButton selected = (RadioButton)findViewById(sizes.getCheckedRadioButtonId());
        selected.setChecked(false);
        orderButton.setEnabled(false);
        disableBoxes();
    }

    /**
     * Method to enable all check boxes
     */
    void clearBoxes() {
        creamBox.setChecked(false);
        syrupBox.setChecked(false);
        milkBox.setChecked(false);
        caramelBox.setChecked(false);
        whipBox.setChecked(false);
    }

    /**
    * Method to enable all check boxes
    */
    void disableBoxes() {
        creamBox.setEnabled(false);
        syrupBox.setEnabled(false);
        milkBox.setEnabled(false);
        caramelBox.setEnabled(false);
        whipBox.setEnabled(false);
    }

    /**
     * Method to disable all check boxes
     */
    void enableBoxes() {
        creamBox.setEnabled(true);
        syrupBox.setEnabled(true);
        milkBox.setEnabled(true);
        caramelBox.setEnabled(true);
        whipBox.setEnabled(true);
    }



}