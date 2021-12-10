package com.example.cse226_end_term.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse226_end_term.ProductDetail;
import com.example.cse226_end_term.R;
import com.example.cse226_end_term.model.ProductModel;


import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewholder> {

    ArrayList<ProductModel> list;
    Context context;

    public ProductAdapter(ArrayList<ProductModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products_list,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.viewholder holder, int position) {
        final ProductModel model = list.get(position);
        holder.productImage.setImageResource(model.getImage());
        holder.productDesc.setText(model.getDescription());
        holder.productPrice.setText(model.getPrice());
        holder.productName.setText(model.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("desc", model.getDescription());
                intent.putExtra("name", model.getName());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, productPrice, productDesc;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.name);
            productPrice = itemView.findViewById(R.id.price);
            productDesc = itemView.findViewById(R.id.desc);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }




}
