package CustomerRental;

import Login.InputMain;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import JDBC.ConnectJDBC;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;


public class ToRent {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    
    public void RentMovie(String username){
       String movies = "";
        boolean check = true;
        while(check){
        movies = input.InputString("Please enter your movies(Enter \"Quit\" to quit): ");
        if(movies.equalsIgnoreCase("quit")){
            return;
          }
            movies = getMovie(movies);
            if(!movies.equals("Unavailable")){
                check = false; 
            }
          }
         String choice = input.InputString("Your chosen movie is available, do you want to rent it?[Y/N]: ");
         if(choice.equalsIgnoreCase("y")){
           Renting(username, movies);
         }
    }
    
    public String getMovie(String movies){
      String queryMovie = "SELECT * FROM Movies WHERE title = ?";
      try{
          prepare = connect.prepareStatement(queryMovie);
          prepare.setString(1, movies);
          prepare.setBoolean(1, true);
          result = prepare.executeQuery();
          if(result.next()){
              prepare = connect.prepareStatement("SELECT RETURN_DATE FROM CUSTOMER_INFO WHERE RETURN_DATE LIKE ?");
              prepare.setString(1, "%Done");
              result = prepare.executeQuery();
              if(result.next()){
                  System.out.println("Your movie is already being paid");
                  return "Unavailable";
              }else{
                return movies;
              }
          }else{
              System.out.println("Your chosen movie is unavailable");
              return "Unavailable";
          }
          
      }catch(SQLException e){
          System.out.println("Connect error" + e.getMessage());
      }
        return movies;
    }
    
    public void Renting(String username, String movie){
        double price = getPrice(movie);
        String queryRental = "INSERT INTO CUSTOMER_INFO (MOVIE, RENTAL_DATE, RETURN_DATE, PRICE, PAYBACK, CHANGE) VALUES (?, ?, ?, ?, ?, ?)";
        RentInfo info = new RentInfo(username, movie, price);
        System.out.printf("Your price of the move \"%s\" is %.2f\n", movie, price);
        System.out.printf("Please make sure to return the movie in %s, or you will have to pay extra 5000VND per day late", info.getReturnDate());
        double pay = 0;
        while(pay <= price){
            pay = input.InputDouble("Please enter number for paying: ");
        }
        double change = price - pay;
        try{
          prepare = connect.prepareStatement(queryRental);
          prepare.setString(1, username);
          prepare.setString(2, movie);
          prepare.setString(3, info.getRentalDate());
          prepare.setString(4, info.getReturnDate());
          prepare.setDouble(5, price);
          prepare.setDouble(6, pay);
          prepare.setDouble(7, change);
            System.out.println("You have successful paying for renting the movie. Have a nice movie!");
        }catch(SQLException e){
            System.out.println("Connect error" + e.getMessage());
        }
    }
    
    public double getPrice(String movie){
        String query = "SELECT * FROM Movies WHERE title = ?";
        try{
            prepare = connect.prepareStatement(query);
            prepare.setString(2, movie);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getDouble(6);
            }else{
                System.out.println("There is no movie here.");
            }
        }catch(SQLException e){
            System.out.println("Connect error" + e.getMessage());
        }
        return 0;
    }
}