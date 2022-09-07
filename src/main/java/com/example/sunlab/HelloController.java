package com.example.sunlab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class HelloController {

    @FXML
    private Label message;

    @FXML
    private TextField input;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField pWord;

    @FXML
    Button loginButton;

    @FXML
    Button cancelButton;

    @FXML
    Label loginMessage;


    public void onAdminButtonClick(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Admin-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("SUN Lab Access");
        stage.setScene(scene);
        stage.show();
    }

    public void onStudentButtonClick(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Student-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("SUN Lab Access");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackButtonClicked(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("SUN Lab Access");
        stage.setScene(scene);
        stage.show();
    }
    public void onCancelButtonClicked(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("SUN Lab Access");
        stage.setScene(scene);
        stage.show();
    }

    public void onLoginButtonClick(ActionEvent e) throws IOException {

        if (userName.getText().isBlank() == true) {
            loginMessage.setText("enter user name");
            return;
        }
        if (pWord.getText().isBlank() == true) {
            loginMessage.setText("enter password");
            return;
        }

        if (userName.getText().equals("root") && pWord.getText().equals("Mohinderv@2022")) {
            loginMessage.setText("correct password");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Query-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setTitle("SUN Lab Access");
            stage.setScene(scene);
            stage.show();

        } else {
            loginMessage.setText("wrong cred");
        }

    }

    public boolean checkNumeric(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) < 48 || a.charAt(i) > 57)
                return false;
        }
        return true;
    }

    public String getdate() {
        //DateFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return now.toString();
    }

    public String gettime() {
        LocalTime now = LocalTime.now();
        return now.toString();
    }

    public void storeindb(int result) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectdb = connectNow.getconnected();
        try {
            String query1 = "SELECT COUNT(*) FROM sun_lab_system.sun_lab_access WHERE StudentID=" + result + " AND outsideDate IS NULL;";
            Statement statement = connectdb.createStatement();
            ResultSet qres1 = statement.executeQuery(query1);

            qres1.next();
            int count = qres1.getInt(1);
            if (count == 1) {
                String query2 = "UPDATE sun_lab_system.sun_lab_access SET outsideDate='" + getdate() + "', outsideTime='" + gettime() + "' WHERE StudentID=" + result + " AND outsideDate IS NULL;";
                // Statement statement2= connectdb.createStatement();

                int qres2 = statement.executeUpdate(query2);
            } else if (count == 0) { //message.setText(getdateandtime());
                String query3 = "INSERT INTO sun_lab_system.sun_lab_access (StudentID, insideDate, outsideDate, insideTime, outsideTime) VALUES ('" + result + "', '" + getdate() + "', NULL, '" + gettime() + "', NULL)";
                int res = statement.executeUpdate(query3);

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void onIDButtonClick(ActionEvent e) throws IOException {


        int result;
        if (input.getText().isBlank() == true) {
            message.setText("enter student ID");
            return;
        } else {
            String id = input.getText();
            if (id.length() < 9) {
                message.setText("Invalid student ID");
                return;
            }
            if (id.length() == 9 && checkNumeric(id)) {
                result = Integer.valueOf(id);
                storeindb(result);
                message.setText("Valid student ID");
                return;
            }

            if (id.length() > 12) {
                if ((id.charAt(0) == '%') && (id.charAt(1) == 'A')) {
                    String ID = "";
                    for (int i = 2; i <= 10; i++) {
                        ID += id.charAt(i);
                    }
                    result = Integer.valueOf(ID);
                    storeindb(result);
                    message.setText("Valid student ID");
                } else {
                    message.setText("Invalid student ID");
                    return;
                }
            }
        }
    }

}