/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerRental;

import JDBC.ConnectJDBC;
import Login.InputMain;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author HuyDepZai
 */
public class Search {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    public void search() throws SQLException{
        String search = input.InputString("Please enter the movie you want to search: ");
        String query = "SELECT * FROM Movie WHERE Title LIKE ?";
        try{
            prepare = connect.prepareStatement(query);
            prepare.setString(1, "%" + search + "%");
            result = prepare.executeQuery();
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-20s|%-30s|%-30s|%-20s|%-30s|%-20s|%-15s|\n", 
                              "MovieID", "Title", "Description", "Rating", "Availability", 
                              "Rental Price", "Year of Release");
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            int count = 0;
            while (result.next()) {
                int movieId = result.getInt("movie_id");
                String title = result.getString("title");
                String description = result.getString("description");
                BigDecimal rating = result.getBigDecimal("rating");
                boolean availability = result.getBoolean("availability");
                BigDecimal rentalPrice = result.getBigDecimal("rental_price");
                int yearOfRelease = result.getInt("year_of_release");

                System.out.printf("|%-20d|%-30s|%-30s|%-20.1f|%-30s|%-20.2f|%-15d|%n", 
                                  movieId, 
                                  title, 
                                  description, 
                                  rating.floatValue(), 
                                  availability ? "Available" : "Not Available", 
                                  rentalPrice.doubleValue(), 
                                  yearOfRelease);
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            count++;
            }
            if(count > 1)
              System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");                 
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
