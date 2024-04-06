module com.example.lista {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.lista to javafx.fxml;
    exports com.example.lista;
}