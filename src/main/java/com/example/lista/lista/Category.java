package com.example.lista.lista;


import java.util.ArrayList;
import java.util.List;

public class Category {
    private  String name;
    private  List<Product> Products;
    public int prodNumber;
    public Category(String name){
        this.name = name;
        this.Products = new ArrayList<>();
        prodNumber = 0;
    }

    public void addProduct(String name,String typeOfProduct, Type typ){
        Products.add(new Product(name, typeOfProduct, typ));
    }
    public List<Product> getProducts(){
        return this.Products;
    }
    public String getName(){
        return this.name;
    }
    public static void printCategoryContent(Category cat){
        int i = 1;
        for (Product prod : cat.Products) {
            String nazwa = prod.getName();
            System.out.println(i + ". " + nazwa);
            i++;
        }
    }
    public static String setName2(Category cat, int number){
        int j =1;
        String nazwa = null;
        List<Product> P = cat.getProducts();
        for (Product prod : P) {
            if(j==number) {
                nazwa = prod.getName();
                return nazwa;
            }
            j++;
        }
        return null;
    }

    public static String setTypeOfProduct(Category cat, int number){
        int j =1;
        String nazwa = null;
        List<Product> P = cat.getProducts();
        for (Product prod : P) {
            if(j==number) {
                nazwa = prod.getTypeOfProduct();
                return nazwa;
            }
            j++;
        }
        return null;
    }
    public static Type setType(Category cat, int number){
        int j =1;
        List<Product> P = cat.getProducts();
        for (Product prod : P) {
            if(j==number) {
                return prod.getTyp();
            }
            j++;
        }
        return null;
    }
    public static int setStal(Category cat, int number){
        int j =1;
        List<Product> P = cat.getProducts();
        for (Product prod : P) {
            if(j==number) {
                return prod.getPriceStal();
            }
            j++;
        }
        return 0;
    }

    public static double setZmien(Category cat, int number){
        int j =1;
        List<Product> P = cat.getProducts();
        for (Product prod : P) {
            if(j==number) {
                return prod.getPriceZmien();
            }
            j++;
        }
        return 0;
    }
    public static void deleteProduct(Category cat, int number){
        List<Product> P = cat.getProducts();
        P.remove(number-1);
    }
}
