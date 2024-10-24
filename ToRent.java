package CustomerRental;

import Login.InputMain;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import JDBC.ConnectJDBC;
import java.sql.Connection;


public class ToRent {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    
    public void RentMovie(String username){
        boolean check = true;
        while(check){
        String queryRental = "INSERT INTO CUSTOMER_INFO (MOVIE, RENTAL_DATE, RETURN_DATE, PRICE, PAYBACK, CHANGE) VALUES (?, ?, ?, ?, ?, ?)";
        String movies = input.InputString("Please enter your movies(Enter \"Quit\" to quit): ");
        if(movies.equalsIgnoreCase("quit")){
            return;
          }else{
            String movie = getMovie(movies);
          }
        }
    }
    
    public String getMovie(String movies){
      String queryMovie = "SELECT * FROM Movies";
      
        return "";
    }
}