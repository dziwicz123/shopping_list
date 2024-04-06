package com.example.lista.lista;
public class Product {
    private  String name;
    private Type typ;
    private String typeOfProduct;
    private int amountStal = 0;
    private double amountZmien = 0;
    private int  priceStal = 1;
    private double  priceZmien = 0;

    public int getPriceStal() {
        return priceStal;
    }

    public void setPriceStal(int priceStal) {
        this.priceStal = priceStal;
    }

    public double getPriceZmien() {
        return priceZmien;
    }

    public void setPriceZmien(double priceZmien) {
        this.priceZmien = priceZmien;
    }
    public Type getTyp() {
        return typ;
    }

    public int getAmountStal() {
        return amountStal;
    }
    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public int getAmount() {
        return amountStal;
    }

    public void setAmount(int amount) {
        this.amountStal = amount;
    }

    public void setAmountStal(int amountStal) {
        this.amountStal = amountStal;
    }

    public double getAmountZmien() {
        return amountZmien;
    }

    public void setAmountZmien(double amountZmien) {
        this.amountZmien = amountZmien;
    }
    public  Product(String name, String typeOfProduct, Type typ){
        this.name = name;
        this.typeOfProduct = typeOfProduct;
        this.typ = typ;
    }

    public String getName(){
        return name;
    }


}
