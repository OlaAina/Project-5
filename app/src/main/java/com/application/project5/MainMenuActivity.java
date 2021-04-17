package com.application.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
    private RecyclerView itemRecycler;
    private RecyclerView.Adapter mainMenuAdapter;
    private RecyclerView.LayoutManager mainLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ArrayList<MainMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainMenuItem("Order Coffee", R.drawable.coffee));
        menuItems.add(new MainMenuItem("Order Donut", R.drawable.donut));
        menuItems.add(new MainMenuItem("Order Details", R.drawable.cart));
        menuItems.add(new MainMenuItem("Store Orders", R.drawable.orders));

        itemRecycler = findViewById(R.id.itemRecycler);
        itemRecycler.setHasFixedSize(true);
        mainLayoutManager = new LinearLayoutManager(this);
        mainMenuAdapter = new MainMenuAdapter(menuItems, this);

        itemRecycler.setLayoutManager(mainLayoutManager);
        itemRecycler.setAdapter(mainMenuAdapter);

    }
}
