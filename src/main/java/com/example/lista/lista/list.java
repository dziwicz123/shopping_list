package com.example.lista.lista;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
public class list {
    private List<Category> Kategorie;
    public int catNumber;

    public list(){
        Kategorie = new ArrayList<>();
        catNumber = 0;
    }
    public void addCategory(String name){

        Kategorie.add(new Category(name));
    }
    public List<Category> getCategory(){
        return this.Kategorie;
    }

    public Category getCategoryByName(String name){

        for (Category kategoria : this.Kategorie){
            if(kategoria.getName().equals(name)){
                return kategoria;
            }
        }
        return null;

    }

    public static void saveList(list lista, File file){
        try {
            FileWriter writer = new FileWriter(file);
            List<Category> cat = lista.getCategory();
            for (Category kategoria : cat) {
                String name = kategoria.getName();
                List<Product> P = kategoria.getProducts();
                for (Product prod : P) {
                    String nazwa = prod.getName();
                    if(prod.getTyp()==Type.stalo){
                        writer.write(name + "|" + nazwa + "|" + prod.getTypeOfProduct() + "|" + prod.getAmountStal() + "\n");
                    }
                    else{
                        writer.write(name + "|" + nazwa + "|" + prod.getTypeOfProduct() + "|" + prod.getAmountZmien() + "\n");
                    }
                }
            }
            writer.close();

        } catch (IOException e){
            System.out.println("Something gone wrong");
        }
    }

    public static void printCategoris(list lista){
        List<Category> cat = lista.getCategory();
        int i = 1;
        for (Category kategoria : cat){
            String name = kategoria.getName();
            System.out.println(i + ". " + name);
            i++;
        }
    }

    public static Category returnCategoryByNumber(list lista, int number){
        List<Category> cat = lista.getCategory();
        int i = 1;
        for (Category kategoria : cat){
            if(i == number){
                return kategoria;
            }
            i++;
        }
        return null;
    }

    public static void deleteAll(list lista){
        List<Category> cat = lista.getCategory();
        for (Category kategoria : cat) {
            List<Product> P = kategoria.getProducts();
            P.clear();
        }
    }

    public static void deleteCategory(list lista, int number){
        int i = 1;
        List<Category> cat = lista.getCategory();
        for (Category kategoria : cat) {
            if(i==number) {
                List<Product> P = kategoria.getProducts();
                P.clear();
                break;
            }
            i++;
        }
    }
}
