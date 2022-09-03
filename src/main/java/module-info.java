module com.example.sunlab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sunlab to javafx.fxml;
    exports com.example.sunlab;
}