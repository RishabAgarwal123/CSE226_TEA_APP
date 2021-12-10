package com.example.cse226_end_term;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cse226_end_term.adapter.ProductAdapter;
import com.example.cse226_end_term.model.ProductModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FirstProductActivity extends AppCompatActivity {
    ArrayList<ProductModel> list;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_product);

        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("TEA PRODUCTS");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        list = new ArrayList<>();
        recyclerView = findViewById(R.id.teaRv);
        fab = findViewById(R.id.fab);

        list.add(new ProductModel("Masala Tea", "25", "Special", R.drawable.masala_tea));
        list.add(new ProductModel("Dhum Tea", "25", "Special", R.drawable.dhum_tea));
        list.add(new ProductModel("Black Tea", "25", "Special", R.drawable.black_tea));
        list.add(new ProductModel("Ginger Tea", "35", "Special", R.drawable.ginger_tea));
        list.add(new ProductModel("Green Tea", "55", "Special", R.drawable.green_tea));

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
