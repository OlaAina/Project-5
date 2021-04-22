package com.application.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
/**
 * Class that controls the Main menu Activity
 * Provides the items for the recycler view
 * @author Adeola Adebanjo, Olaolu Aina
 *
 */
public class MainMenuActivity extends AppCompatActivity {
    private RecyclerView itemRecycler;
    private RecyclerView.Adapter mainMenuAdapter;
    private RecyclerView.LayoutManager mainLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ArrayList<MainMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainMenuItem(getString(R.string.order_coffee), R.drawable.coffee));
        menuItems.add(new MainMenuItem(getString(R.string.order_donut), R.drawable.donut));
        menuItems.add(new MainMenuItem(getString(R.string.order_details), R.drawable.cart));
        menuItems.add(new MainMenuItem(getString(R.string.store_orders), R.drawable.orders));

        itemRecycler = findViewById(R.id.itemRecycler);
        itemRecycler.setHasFixedSize(true);
        mainLayoutManager = new LinearLayoutManager(this);
        mainMenuAdapter = new MainMenuAdapter(menuItems, this);

        itemRecycler.setLayoutManager(mainLayoutManager);
        itemRecycler.setAdapter(mainMenuAdapter);

        Order order= OrderDetailActivity.getOrder();
        MenuItem[] items= new MenuItem[4];
        order.setOrder(items);

        StoreOrders storeOrders= StoreOrdersActivity.getStoreOrders();
        Order[] orders= new Order[4];
        storeOrders.setOrderList(orders);
    }
}
