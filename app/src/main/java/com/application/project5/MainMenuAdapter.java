package com.application.project5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>{
    private ArrayList<MainMenuItem> itemList;
    private RecyclerView mainRecycler;
    private Context context;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int itemPosition = mainRecycler.getChildLayoutPosition(view);
            Intent intent;
            switch(itemPosition){
                case 0:
                    intent= new Intent(context, DonutActivity.class);
                    break;
                case 1:
                    intent= new Intent(context, CoffeeActivity.class);
                    break;
                case 2:
                    intent= new Intent(context, OrderDetailActivity.class);
                    break;
                case 3:
                    intent= new Intent(context, StoreOrdersActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + itemPosition);
            }
            context.startActivity(intent);
        }
    };

    public MainMenuAdapter(ArrayList<MainMenuItem> menuList) {
    }

    public static class MainMenuViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageResource;
        private TextView item;

        public MainMenuViewHolder(View itemView) {
            super(itemView);
            imageResource=itemView.findViewById(R.id.itemImage);
            item= itemView.findViewById(R.id.itemText);
        }
    }

    public MainMenuAdapter(ArrayList<MainMenuItem> itemArrayList, Context context){
        itemList= itemArrayList;
        this.context= context;
    }


    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_placeholder,parent,false);
        MainMenuViewHolder main= new MainMenuViewHolder(v);
        v.setOnClickListener(mOnClickListener);
        return main;
    }

    @Override
    public void onBindViewHolder(MainMenuViewHolder holder, int position) {
        MainMenuItem currentItem=itemList.get(position);

        holder.imageResource.setImageResource(currentItem.getImageResource());
        holder.item.setText(currentItem.getItem());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mainRecycler = recyclerView;
    }

}

