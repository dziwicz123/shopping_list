package com.example.lista;

import com.example.lista.lista.Product;
import com.example.lista.lista.Type;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.example.lista.lista.Category;
import com.example.lista.lista.list;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import java.io.File;
import java.util.List;
import javafx.scene.control.TextField;

import static com.example.lista.lista.Category.*;
import static com.example.lista.lista.list.*;


public class HelloApplication extends Application {
    private Button addButton;
    private Button addProductButton;
    private Button addCategoryButton;
    private Button displayListButton;
    private Button displayCategoryButton;
    private Button deleteAllButton;
    private Button deleteCategoryButton;
    private Button deleteProductButton;
    private Button saveListButton;
    static list lista = new list();
    static list listaZakup = new list();

    private TextArea textArea;

    private static final int ADD = 1;
    private static final int ADD_PRDUCT = 5;

    private static final int SHOW = 2;
    private static final int DELETE_CATEGORY= 3;
    private static final int DELETE_PRODUCT= 4;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista zakupów");
        VBox root = new VBox(10);

        generateHomePage(primaryStage, root);

        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-min-width: 200px; -fx-min-height: 40px;");
        return button;
    }

    public static void main(String[] args) {
        Category kategoria = new Category("Spożywcze");
        listaZakup.addCategory("Spożywcze");
        Product prod1 = new Product("Chleb", "sztuki", Type.stalo);
        Product prod2 = new Product("Masło","kilogramy", Type.zmienny);
        prod2.setPriceZmien(0.5);
        Product prod3 = new Product("Mleko","litry", Type.zmienny);
        prod3.setPriceZmien(1.5);
        Product prod4 = new Product("Żółty ser","kilogramy", Type.zmienny);
        prod4.setPriceZmien(0.3);
        kategoria.getProducts().add(prod1);
        kategoria.getProducts().add(prod2);
        kategoria.getProducts().add(prod3);
        kategoria.getProducts().add(prod4);
        kategoria.prodNumber += 4;
        lista.getCategory().add(kategoria);

        Category kategoria1 = new Category("Chemia");
        listaZakup.addCategory("Chemia");
        Product prod5 = new Product("Mydło", "sztuki",Type.stalo);
        Product prod6 = new Product("Płyn do mycia naczyń","litry", Type.zmienny);
        prod6.setPriceZmien(0.75);
        kategoria1.prodNumber += 2;
        kategoria1.getProducts().add(prod5);
        kategoria1.getProducts().add(prod6);
        lista.getCategory().add(kategoria1);

        Category kategoria2 = new Category("Motoryzacja");
        listaZakup.addCategory("Motoryzacja");
        Product prod7 = new Product("Odświeżacz powietrza", "sztuki",Type.stalo);
        Product prod8 = new Product("Płyn do spryskiwaczy", "litry", Type.zmienny);
        prod8.setPriceZmien(3);
        kategoria2.getProducts().add(prod7);
        kategoria2.getProducts().add(prod8);
        kategoria2.prodNumber += 2;
        lista.getCategory().add(kategoria2);


        launch();
    }

    private EventHandler<ActionEvent> generateHomePage(Stage primaryStage, VBox root){
        root.getChildren().clear();

        addButton = createStyledButton("Dodaj do listy");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addButton.setOnAction(showCategoriesScreen(primaryStage, lista, listaZakup, root,ADD));
            }
        });

        addProductButton = createStyledButton("Dodaj produkt");
        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addProductButton.setOnAction(showCategoriesScreen(primaryStage, lista, listaZakup, root,ADD_PRDUCT));
            }
        });

        addCategoryButton = createStyledButton("Dodaj kategorię");
        addCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addCategoryButton.setOnAction(CreateCategory(primaryStage, root, listaZakup, lista));
            }
        });


        displayListButton = createStyledButton("Wyświetl listę");
        displayListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printList(primaryStage,listaZakup,root);
            }
        });

        displayCategoryButton = createStyledButton("Wyświetl kategorię");
        displayCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addButton.setOnAction(showCategoriesScreen(primaryStage, lista, listaZakup, root,SHOW));
            }
        });

        deleteAllButton = createStyledButton("Usuń wszystko");
        deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteAll(listaZakup);
            }
        });

        deleteCategoryButton = createStyledButton("Usuń kategorię");
        deleteCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addButton.setOnAction(showCategoriesScreen(primaryStage, lista, listaZakup, root,DELETE_CATEGORY));
            }
        });

        deleteProductButton = createStyledButton("Usuń produkt");
        deleteProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addButton.setOnAction(showCategoriesScreen(primaryStage, lista, listaZakup, root,DELETE_PRODUCT));
            }
        });

        saveListButton = createStyledButton("Zapisz listę");
        saveListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(primaryStage);
                saveList(listaZakup, file);
                Platform.exit();
            }
        });

        root.getChildren().addAll(addButton, addProductButton, addCategoryButton, displayListButton, displayCategoryButton, deleteAllButton, deleteCategoryButton, deleteProductButton, saveListButton);
        return null;
    }
    private EventHandler<ActionEvent> showCategoriesScreen(Stage primaryStage, list lista, list listaZakup, VBox root, int typ) {
        root.getChildren().clear();
        List<Category> cat = lista.getCategory();
        int  catNumber = 1;
        for (Category kategoria : cat){
            Button catButton = createStyledButton(kategoria.getName());
            int finalCatNumber = catNumber;
            catButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(typ==ADD) {
                        catButton.setOnAction(showProductsScreen(primaryStage, listaZakup, kategoria, root, finalCatNumber,ADD));
                    }
                    else if(typ==ADD_PRDUCT) {
                        catButton.setOnAction(getProductNameFromUser(primaryStage, root, kategoria));
                    }
                    else if(typ==SHOW){
                        catButton.setOnAction(prinCategory(primaryStage,listaZakup,root,finalCatNumber));
                    }
                    else if(typ==DELETE_CATEGORY){
                        deleteCategory(listaZakup,finalCatNumber);
                        generateHomePage(primaryStage, root);
                    }
                    else if(typ==DELETE_PRODUCT){
                        catButton.setOnAction(showProductsScreen(primaryStage, listaZakup, kategoria, root, finalCatNumber,DELETE_PRODUCT));
                    }
                }
            });
            root.getChildren().add(catButton);
            catNumber++;
        }
        return null;
    }

    private EventHandler<ActionEvent> showProductsScreen(Stage primaryStage, list listaZakup, Category kat, VBox root, int catNumber, int typ) {
        root.getChildren().clear();
        List<Category> cat = listaZakup.getCategory();
        Category kategoria1 = returnCategoryByNumber(listaZakup,catNumber);
        int  prodNumber = 1;
        if(typ==ADD) {
            for (Product prod : kat.getProducts()) {
                int finalProdNumber = prodNumber;
                Button catButton = createStyledButton(prod.getName());
                catButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String productName = setName2(kat,finalProdNumber);
                        String typeOfProduct = setTypeOfProduct(kat,finalProdNumber);
                        Type typ = setType(kat,finalProdNumber);
                        List<Product> products = kategoria1.getProducts();
                        for (Product product : products) {
                            if(product.getName().equals(productName)){
                                if(product.getTyp()==Type.stalo) {
                                    product.setAmountStal(product.getAmountStal() + product.getPriceStal());
                                }
                                else if(product.getTyp()==Type.zmienny){
                                    product.setAmountZmien(product.getAmountZmien() + product.getPriceZmien());
                                }
                                else {
                                    System.out.println("Błąd");
                                }
                                generateHomePage(primaryStage, root);
                                return;
                            }

                        }
                        Product prod = new Product(productName,typeOfProduct,typ);
                        if(typ==Type.stalo){
                            prod.setAmountStal(setStal(kat,finalProdNumber));
                        }
                        else{
                            prod.setAmountZmien(setZmien(kat,finalProdNumber));
                            prod.setPriceZmien(setZmien(kat,finalProdNumber));
                        }
                        kategoria1.getProducts().add(prod);
                        generateHomePage(primaryStage, root);
                    }
                });
                root.getChildren().add(catButton);
                prodNumber++;
            }
        }
        else if(typ==DELETE_PRODUCT){
            prodNumber = 1;
            for (Product prod : kategoria1.getProducts()) {
                int finalProdNumber = prodNumber;
                Button catButton = createStyledButton(prod.getName());
                catButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteProduct(kategoria1,finalProdNumber);
                        generateHomePage(primaryStage, root);
                    }
                });
                root.getChildren().add(catButton);
                prodNumber++;
            }
        }

        Button backButton = createStyledButton("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateHomePage(primaryStage, root);
            }
        });
        root.getChildren().add(backButton);
        return null;
    }

    private void printList(Stage primaryStage, list List, VBox root) {
        root.getChildren().clear();
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(380, 460);
        root.getChildren().add(textArea);
        List<Category> categories = List.getCategory();
        StringBuilder sb = new StringBuilder();
        for (Category category : categories) {
            String categoryName = category.getName();
            sb.append(categoryName).append("\n");
            List<Product> products = category.getProducts();
            for (Product product : products) {
                String productName = product.getName();
                if(product.getTyp()==Type.zmienny) {
                    sb.append("- ").append(productName).append(" ").append(product.getTypeOfProduct()).append(" ").append(product.getAmountZmien()).append("\n");
                }
                else if(product.getTyp()==Type.stalo) {
                    sb.append("- ").append(productName).append(" ").append(product.getTypeOfProduct()).append(" ").append(product.getAmountStal()).append("\n");
                }
            }
        }
        textArea.setText(sb.toString());
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateHomePage(primaryStage, root);
            }
        });
        root.getChildren().add(backButton);
    }

    private EventHandler<ActionEvent> prinCategory(Stage primaryStage, list listaZakup, VBox root, int catNumber) {
        root.getChildren().clear();
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(380, 460);
        root.getChildren().add(textArea);
        StringBuilder sb = new StringBuilder();
        Category cat = returnCategoryByNumber(listaZakup,catNumber);
        List<Product> products = cat.getProducts();
        for (Product product : products) {
            String productName = product.getName();
            if(product.getTyp()==Type.zmienny) {
                sb.append("- ").append(productName).append(" ").append(product.getTypeOfProduct()).append(" ").append(product.getAmountZmien()).append("\n");
            }
            else if(product.getTyp()==Type.stalo) {
                sb.append("- ").append(productName).append(" ").append(product.getTypeOfProduct()).append(" ").append(product.getAmountStal()).append("\n");
            }
        }

        textArea.setText(sb.toString());
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateHomePage(primaryStage, root);
            }
        });
        root.getChildren().add(backButton);
        return null;
    }

    private EventHandler<ActionEvent> CreateCategory(Stage primaryStage, VBox root,list listaZakup, list lista){
        root.getChildren().clear();
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Nazwa kategorii");
        Button backButton = createStyledButton("Dodaj");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String categoryName = categoryTextField.getText();
                lista.addCategory(categoryName);
                listaZakup.addCategory(categoryName);
                categoryTextField.clear();
                categoryTextField.setVisible(false);
                generateHomePage(primaryStage, root);
            }
        });
        root.getChildren().addAll(categoryTextField, backButton);
        return null;
    }

    private EventHandler<ActionEvent> getProductNameFromUser(Stage primaryStage, VBox root,Category cat){
        root.getChildren().clear();
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Nazwa produktu");
        Button backButton = createStyledButton("Dalej");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String productName = categoryTextField.getText();
                categoryTextField.clear();
                categoryTextField.setVisible(false);
                getTypeNameFromUser(primaryStage,root,cat,productName);
            }
        });
        root.getChildren().addAll(categoryTextField, backButton);
        return null;
    }

    private EventHandler<ActionEvent> getTypeNameFromUser(Stage primaryStage, VBox root,Category cat, String ProductName){
        root.getChildren().clear();
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Nazwa Typu");
        Button backButton = createStyledButton("Dalej");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String TypeName = categoryTextField.getText();
                categoryTextField.clear();
                categoryTextField.setVisible(false);
                if(TypeName==""){

                }
                getTypeFromUser(primaryStage,root,cat,ProductName,TypeName);
            }
        });
        root.getChildren().addAll(categoryTextField, backButton);
        return null;
    }

    private EventHandler<ActionEvent> getTypeFromUser(Stage primaryStage, VBox root,Category cat, String ProductName, String TypeName){
        root.getChildren().clear();
        Button stalButton = createStyledButton("stałoprzecinkowy");
        stalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Product prod = new Product(ProductName,TypeName,Type.stalo);
                cat.getProducts().add(prod);
                generateHomePage(primaryStage, root);
            }
        });
        Button zmienButton = createStyledButton("zmiennoprzecinkowy");
        zmienButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateProductZmien(primaryStage,root,cat,ProductName,TypeName,Type.zmienny);
            }
        });
        root.getChildren().addAll(zmienButton, stalButton);
        return null;
    }

    private EventHandler<ActionEvent> CreateProductZmien(Stage primaryStage, VBox root,Category cat, String ProductName, String TypeName, Type typ){
        root.getChildren().clear();
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Podaj liczbę");
        Button backButton = createStyledButton("Dodaj");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String numberText = categoryTextField.getText();
                double number = 0.0;
                try {
                    number = Double.parseDouble(numberText);
                    Product prod = new Product(ProductName,TypeName,Type.zmienny);
                    prod.setPriceZmien(number);
                    cat.getProducts().add(prod);
                    generateHomePage(primaryStage, root);
                } catch (NumberFormatException e) {
                    CreateProductZmien(primaryStage,root,cat,ProductName,TypeName,typ);
                }
            }
        });

        root.getChildren().addAll(categoryTextField, backButton);
        return null;
    }
}