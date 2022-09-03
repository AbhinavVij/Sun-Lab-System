package com.example.sunlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    public Connection dblink;

    public  Connection getconnected()
    {
        String dbName="sun_lab_system";
        String dbUserName="root";
        String dbPassword="Mohinderv@2022";
        String URL="jdbc:mysql://localhost/"+dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dblink= DriverManager.getConnection(URL,dbUserName,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

return dblink;
    }
}
