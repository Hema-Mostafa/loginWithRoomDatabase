package com.example.hemamostafa.loginwithroom.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemamostafa.loginwithroom.Model.Item;
import com.example.hemamostafa.loginwithroom.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    ArrayList<Item> items;

    public ItemAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        items= new ArrayList<>();

        viewHolder.nameTV.setText(items.get(i).getName());
        viewHolder.descTV.setText(items.get(i).getDesc());
        viewHolder.imageView.setImageResource(items.get(i).getImage());

    }

    @Override
    public int getItemCount() {
       if (items != null)
           return items.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV;
        TextView descTV;
        ImageView imageView;


        public ViewHolder(@NonNull View view) {
            super(view);
        }
    }

}
