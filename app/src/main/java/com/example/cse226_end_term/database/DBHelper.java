package com.example.cse226_end_term.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cse226_end_term.model.OrderModel;


import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "ordersDB.db";
    final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "price integer," +
                        "image integer," +
                        "description text," +
                        "prodname text," +
                        "quantity integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }



    public boolean insertOrder(int price, int image, String desc, String prodname,int quantity){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("prodname",prodname);
        values.put("quantity",quantity);


        long id = db.insert("orders",null,values);
        if(id <= 0) {
            return false;
        }
        return true;
    }
/*
    public void updateData(OrderModel orderModell){

        ContentValues values = new ContentValues();

        values.put("image",orderModell.getOrderImage());
        values.put("prodname",orderModell.getOrderName());
        values.put("description",orderModell.getOrderDesc());
        values.put("quantity",orderModell.getOrderQuantity());
        values.put("price",orderModell.getOrderPrice());
        values.put("num",orderModell.getOrderNumber());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update("orders",values,"id" + " = ?" , new String[]
                {String.valueOf(orderModell.getId())});

    }

 */

    public void updateOrder(OrderModel orderModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",orderModel.getId());
        contentValues.put("price",orderModel.getOrderPrice());
        contentValues.put("image",orderModel.getOrderImage());
        contentValues.put("description",orderModel.getOrderDesc());
        contentValues.put("prodname",orderModel.getOrderName());
        contentValues.put("quantity",orderModel.getOrderQuantity());


        db.update("orders",contentValues, "id"+" = ?", new String[]{String.valueOf(orderModel.getId())});
    }



    public int deleteOrder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("orders", "id="+id, null);
    }



    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select prodname,image,price,description,quantity,id  from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrderModel model = new OrderModel();
                model.setOrderName(cursor.getString(0));
                model.setOrderImage(cursor.getString(1));
                model.setOrderPrice(cursor.getInt(2) +"");
                model.setOrderDesc(cursor.getString(3));
                model.setOrderQuantity(cursor.getInt(4) +"");
                model.setOrderNumber(String.valueOf(cursor.getInt(5)));
                orders.add(model);
            }
        }
        return orders;
    }




}
