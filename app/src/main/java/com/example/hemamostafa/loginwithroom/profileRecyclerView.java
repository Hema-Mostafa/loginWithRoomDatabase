package com.example.hemamostafa.loginwithroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hemamostafa.loginwithroom.Adapters.ItemAdapter;
import com.example.hemamostafa.loginwithroom.Model.Item;

import java.util.ArrayList;

public class profileRecyclerView extends AppCompatActivity {


    RecyclerView recyclerView;
    ItemAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Item> itemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);
        AddData();
        adapter = new ItemAdapter(itemArrayList);
        linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    public  void AddData() {
        itemArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemArrayList.add(new Item("ibrahim", "student", R.drawable.logo));
        }
    }
}
