/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerRental;

import JDBC.ConnectJDBC;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMovie {
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    
    public void showMovie() {
        String query = "SELECT * FROM Movies";
        try {
            System.out.println("Start");
            prepare = connect.prepareStatement(query);
            System.out.println("Middle");
            result = prepare.executeQuery();
            System.out.println("end");

//            System.out.println("+-----------------------------------------------------------------------------------------------------+");
//            System.out.printf("|%-20s|%-50s|%-60s|%-20s|%-30s|%-30s|%-20s|%n", 
//                              "MovieID", "Title", "Description", "Rating", "Availability", 
//                              "Rental Price", "Year of Release");
//
//            while (result.next()) {
//                int movieId = result.getInt("movies_id");
//                String title = result.getString("title");
//                String description = result.getString("description");
//                BigDecimal rating = result.getBigDecimal("rating");
//                boolean availability = result.getBoolean("availability");
//                BigDecimal rentalPrice = result.getBigDecimal("rental_price");
//                int yearOfRelease = result.getInt("year_of_release");
//
//                System.out.printf("|%-20d|%-50s|%-60s|%-20.1f|%-30s|%-30.2f|%-20d|%n", 
//                                  movieId, 
//                                  title, 
//                                  description, 
//                                  rating.floatValue(), 
//                                  availability ? "Available" : "Not Available", 
//                                  rentalPrice.doubleValue(), 
//                                  yearOfRelease);
//            }
//            System.out.println("+-----------------------------------------------------------------------------------------------------+");
//            
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
