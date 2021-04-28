package com.example.medicineshop;

public class Product_Data {
    int id;
    String name;
    String price;
    String offer;
    String quantity;
    byte[] image;

    public Product_Data()
    {

    }

    public Product_Data(String name, String price, String offer,String quantity, byte[] image) {
        this.name = name;
        this.price = price;
        this.offer = offer;
        this.quantity=quantity;
        this.image = image;
    }

    public Product_Data(int id, String name, String price, String offer,String quantity, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.offer = offer;
        this.quantity=quantity;
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
