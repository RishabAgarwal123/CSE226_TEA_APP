package com.example.cse226_end_term;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse226_end_term.adapter.ProductAdapter;
import com.example.cse226_end_term.model.ProductModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FourthProductActivity extends AppCompatActivity {

    ArrayList<ProductModel> list;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_product);

        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("MILK PRODUCTS");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.milkRv);
        fab = findViewById(R.id.fab);

        list.add(new ProductModel("Cool Milk", "20", "Cool", R.drawable.cool_milk));
        list.add(new ProductModel("Hot Milk", "30", "Hot", R.drawable.hot_milk));

        adapter = new ProductAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
