package com.example.cse226_end_term;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse226_end_term.adapter.OrderAdapter;
import com.example.cse226_end_term.database.DBHelper;
import com.example.cse226_end_term.model.OrderModel;


import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity implements OnItemDeletedListener{

    RecyclerView order_rv;
    Button placeOrderButton;
    TextView orderTotalPrice;
    DBHelper helper;


    ArrayList<OrderModel> list;
    int sum=0,deletesum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("YOUR ORDERS");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        order_rv = findViewById(R.id.order_rv);
        helper = new DBHelper(this);
        orderTotalPrice = findViewById(R.id.orderTotalPrice);
        helper = new DBHelper(this);

       list = helper.getOrders();
        OrderAdapter adapter = new OrderAdapter(list, this);

        adapter.setOnItemDeletedListener(this);

        List<OrderModel> orderModels = helper.getOrders();


        order_rv = findViewById(R.id.order_rv);
        placeOrderButton = findViewById(R.id.placeOrderButton);



        order_rv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        order_rv.setLayoutManager(layoutManager);

        if(orderModels.size() <= 0) {
            placeOrderButton.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "There is no item in the cart", Toast.LENGTH_SHORT).show();
        }


        int finalSum = totalSum();

        orderTotalPrice.setText(String.valueOf(finalSum));


        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ConfirmOrderActivity.class));
            }
        });

    }

    public int  totalSum() {

        for(int i=0;i<list.size();i++)
        {
            sum += Integer.parseInt(list.get(i).getOrderPrice());
        }
        return sum;
    }



    @Override
    public void onItemDeleted() {
        int total = afterDeleteSum();
        orderTotalPrice.setText(String.valueOf(total));
    }

    private int afterDeleteSum() {
        for(int i=0;i<list.size();i++)
        {
            deletesum += Integer.parseInt(list.get(i).getOrderPrice());
        }
        return deletesum;
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
