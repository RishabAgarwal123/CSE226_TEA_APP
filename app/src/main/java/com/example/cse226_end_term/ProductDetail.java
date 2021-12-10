package com.example.cse226_end_term;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cse226_end_term.database.DBHelper;

public class ProductDetail extends AppCompatActivity {

    ImageView imageProd;
    TextView nameTextView, priceTextView,descTextView,quantity;
    Button addToCartButton;
    ImageView incrementImage, decrementImage;
    int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("Product");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageProd = findViewById(R.id.imageProd);
        nameTextView = findViewById(R.id.nameTextView);
        priceTextView = findViewById(R.id.priceTextView);
        descTextView = findViewById(R.id.descTextView);
        incrementImage = findViewById(R.id.incrementImage);
        decrementImage = findViewById(R.id.decrementImage);

        quantity = findViewById(R.id.quantity);

        addToCartButton = findViewById(R.id.addToCartButton);
        final DBHelper helper = new DBHelper(this);

        final int image = getIntent().getIntExtra("image", 0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String name = getIntent().getStringExtra("name");
        final String description = getIntent().getStringExtra("desc");

        imageProd.setImageResource(image);
        priceTextView.setText(String.format("%d", price));
        nameTextView.setText(name);
        descTextView.setText(description);


        incrementImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                quantity.setText("" + count);
            }
        });
        decrementImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count <= 0) {
                    count = 0;
                }
                else {
                    count--;
                }
                quantity.setText("" + count);
            }
        });



        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalPrice = Integer.parseInt(quantity.getText().toString())*price;
                boolean isInserted = helper.insertOrder(
                        totalPrice,
                        image,
                        description,
                        name,
                        Integer.parseInt(quantity.getText().toString()));

                if (isInserted) {
                    Toast.makeText(ProductDetail.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
                } else {
                    Toast.makeText(ProductDetail.this, "Item not added to cart ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ItemActivity.class));
                }
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
