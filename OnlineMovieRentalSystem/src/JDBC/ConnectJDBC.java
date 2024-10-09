/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ngoct
 */
public class ConnectJDBC {

    public static Connection connection;

    public static void init() {
        String hostname = "localhost";
        String SQL_DB = "OnlineMovieRentalSystem";
        String userName = "sa";
        String password = "12345";
        String url = "jdbc:sqlserver://" + hostname
                + ",databaseName=" + SQL_DB
                + ",trustServerCertificate=true";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            ConnectJDBC.connection = connection;
            System.out.println("Connect Successfully!!");
        } catch (SQLException ex) {
            System.out.println("Connection Error!!");
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
