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

public class ThirdProductActivity extends AppCompatActivity {

    ArrayList<ProductModel> list;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_product);

        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("SNACKS");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.snacksRv);
        fab = findViewById(R.id.fab);

        list.add(new ProductModel("Osmania Biscuit", "15", "Wheat", R.drawable.biscuit_1));
        list.add(new ProductModel("Chocolate Biscuit", "10", "Chocolate", R.drawable.biscuit_2));

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
