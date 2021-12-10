package com.example.cse226_end_term.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse226_end_term.OnItemDeletedListener;
import com.example.cse226_end_term.R;
import com.example.cse226_end_term.database.DBHelper;
import com.example.cse226_end_term.model.OrderModel;


import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder> {
    int count=0;
    private OnItemDeletedListener onItemDeletedListener;
    ArrayList<OrderModel> list;
    Context context;
    DBHelper helper;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
        helper = new DBHelper(context);
    }

    public void setOnItemDeletedListener(Object object) {
        onItemDeletedListener = (OnItemDeletedListener) object;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        final OrderModel model = list.get(position);
        holder.orderImage.setImageResource(Integer.parseInt(model.getOrderImage()));
        holder.orderName.setText(model.getOrderName());
        holder.orderPrice.setText(model.getOrderPrice());
        holder.orderDesc.setText(model.getOrderDesc());
        holder.orderQuantity.setText(model.getOrderQuantity());
        holder.orderNumber.setText(model.getOrderNumber());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final OrderModel orderModel = list.get(holder.getAdapterPosition());
                final String quantity = orderModel.getOrderQuantity();
                final String price = orderModel.getOrderPrice();
                final String name = orderModel.getOrderName();
                final String desc = orderModel.getOrderDesc();
                final String image = orderModel.getOrderPrice();
                final String orderNum = orderModel.getOrderNumber();


                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                count = Integer.parseInt(quantity);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);

                dialog.show();


                final ImageView decrementImage = dialog.findViewById(R.id.decrementUpdateImage);
                final ImageView incrementImage = dialog.findViewById(R.id.incrementUpdateImage);
                final TextView quantityUpdate = dialog.findViewById(R.id.quantityUpdate);
                Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

                quantityUpdate.setText(quantity);

                incrementImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count++;
                        quantityUpdate.setText("" + count);
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
                        quantityUpdate.setText("" + count);
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uQuantity = quantityUpdate.getText().toString();
                        helper.updateOrder(new OrderModel(orderModel.getId(),
                                price,
                                image,
                                desc,
                                name,
                                uQuantity));

                        dialog.dismiss();
                        notifyDataSetChanged();
                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());

                    }
                });




            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper helper = new DBHelper(context);
                                if(helper.deleteOrder(model.getOrderNumber())> 0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    list.remove(position);
                                    notifyDataSetChanged();
                                    onItemDeletedListener.onItemDeleted();

                                }
                                else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

                return false;
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView orderDesc, orderPrice,orderQuantity,orderName,orderNumber;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.order_productImage);
            orderName = itemView.findViewById(R.id.order_productName);
            orderDesc = itemView.findViewById(R.id.order_productDesc);
            orderPrice = itemView.findViewById(R.id.order_productPrice);
            orderQuantity = itemView.findViewById(R.id.order_productQuantity);
            orderNumber = itemView.findViewById(R.id.order_number);

        }
    }
}
