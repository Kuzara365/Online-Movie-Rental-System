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
       String moviesId = "";
       String movies = "";
        boolean check = true;
        while(check){
        moviesId = input.InputString("Please enter your moviesID(Enter \"Quit\" to quit): ");
        if(moviesId.equalsIgnoreCase("quit")){
            return;
          }
            movies = getMovie(moviesId);
            if(!movies.equals("Unavailable")){
                check = false; 
            }
          }
         String choice = input.InputString("Your chosen movie is available, do you want to rent it?[Y/N]: ");
         if(choice.equalsIgnoreCase("y")){
           Renting(username, movies, moviesId);
         }
    }
    
    public String getMovie(String movies){
      String queryMovie = "SELECT * FROM Movie WHERE movie_id = ? AND availability = ?";
      try{
          prepare = connect.prepareStatement(queryMovie);
          prepare.setString(1, movies);
          prepare.setBigDecimal(2, BigDecimal.ONE);
          result = prepare.executeQuery();
          String find = "";
          if(result.next()){
                find = result.getString("title");
                return find;
          }else{
              System.out.println("Your chosen movie is unavailable");
              return "Unavailable";
          }
          
      }catch(SQLException e){
          System.out.println("Connect error" + e.getMessage());
      }
        return movies;
    }
    
public void Renting(String username, String movie, String moviesId){
    double price = getPrice(movie);
    String queryRental = "INSERT INTO HISTORY (CUSTOMER, MOVIE, RENTAL_DATE, RETURN_DATE, PRICE, PAYBACK, CHANGE) VALUES (?, ?, ?, ?, ?, ?, ?)";
    RentInfo info = new RentInfo(username, movie, price);
    System.out.printf("Your price of the move \"%s\" is %.2f\n", movie, price);
    System.out.printf("Please make sure to return the movie in %s, or you will have to pay extra 5 per day late", info.getReturnDate());
    System.out.println("");
    double pay = 0;
    while(pay < price){
        pay = input.InputDouble("Please enter number for paying: ");
    }
    double change = Math.abs(price - pay);
    System.out.printf("Your change is %.2f.\n", change);
    try{
        prepare = connect.prepareStatement(queryRental);
        prepare.setString(1, username);
        prepare.setString(2, movie);
        prepare.setString(3, info.getRentalDate());
        prepare.setString(4, info.getReturnDate());
        prepare.setDouble(5, price);
        prepare.setDouble(6, pay);
        prepare.setDouble(7, change);
        prepare.executeUpdate();
        System.out.println("You have successfully paid for renting the movie. Have a nice movie!");

        String update = "UPDATE Movie SET availability = ? WHERE movie_id = ?";
        prepare = connect.prepareStatement(update);
        prepare.setInt(1, 0);
        prepare.setString(2, moviesId);
        prepare.executeUpdate();

    } catch(SQLException e) {
        System.out.println("Connect error: " + e.getMessage());
    }
}
    
    public double getPrice(String movie){
        String query = "SELECT * FROM Movie WHERE title = ?";
        try{
            prepare = connect.prepareStatement(query);
            prepare.setString(1, movie);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getDouble("rental_price");
            }else{
                System.out.println("There is no movie here.");
            }
        }catch(SQLException e){
            System.out.println("Connect error" + e.getMessage());
        }
        return 0;
    }
}