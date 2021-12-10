package com.example.cse226_end_term.model;

public class OrderModel {

    String orderImage;
    Integer id;
    String orderName, orderDesc, orderQuantity ,orderPrice,orderNumber;

    public OrderModel( String orderPrice,String orderImage, String orderDesc, String orderName,String orderQuantity, String orderNumber) {
        this.orderPrice = orderPrice;
        this.orderDesc = orderDesc;
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.orderNumber = orderNumber;
    }


    public OrderModel(Integer id,String orderPrice,String orderImage, String orderDesc, String orderName,String orderQuantity) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.orderDesc = orderDesc;
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
    }

    public OrderModel() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}


