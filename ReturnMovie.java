/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerRental;

import java.sql.SQLException;
import JDBC.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Login.InputMain;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author HuyDepZai
 */
public class ReturnMovie {
    private String username;
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    
    public ReturnMovie(String username){
        this.username = username;
    }
    
    public void MainTOReturn(){       
        String movie = checkMovie();
        if(!movie.equals("Unavailable")){
        //    double pay = Payment(movie);
            int missing = Late(movie);
            System.out.println(missing);
            Returning(movie, missing);
        }
    }
    
public int Late(String movie) {
    int count = 0;
    try {
        String today = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern(" HH:mm:ss"));
        
        // Query for the movie info
        prepare = connect.prepareStatement("SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?");
        prepare.setString(1, username);
        prepare.setString(2, movie);
        result = prepare.executeQuery();

        if (result.next()) {
            String returnDate = result.getString("RETURN_DATE");

            // Check if RETURN_DATE is "Done" or a valid date
            if (!returnDate.equals("Done")) {
                LocalDateTime now = LocalDateTime.parse(today, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime returndate = LocalDateTime.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (now.isAfter(returndate)) {
                    count = (int) ChronoUnit.DAYS.between(returndate.toLocalDate(), now.toLocalDate());
                }
            } else {
                System.out.println("The movie has already been returned.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Connection error: " + e.getMessage());
    }
    return count;
}

    
//    public double Payment(String movie){
//        try{
//            prepare = connect.prepareStatement("SELECT PRICE FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?");
//            prepare.setString(1, username);
//            prepare.setString(2, movie);
//            result = prepare.executeQuery();
//            if(result.next()){
//                return result.getDouble("PRICE");
//            }
//        }catch(SQLException e){
//            System.out.println("Connect error: " + e.getMessage());
//        }
//        return 0;
//    }
    
    public void Returning(String movie, int missing){
        System.out.println("Hello");
        String queryMovie = "UPDATE CUSTOMER_INFO SET PRICE = ?, PAYBACK = ?, CHANGE = ?, RETURN_DATE = ? WHERE CUSTOMER = ? AND MOVIE = ?";
        double price = 5 * missing;
        double payback = 0;
         while(payback < price){
             payback = input.InputDouble("Please enter number for paying: ");
         }
         double change = price - payback;
         System.out.printf("Your change is: %.2f \n", change);
        try{
//          prepare = connect.prepareStatement(queryMovie);
//          prepare.setString(1, username);
//          prepare.setString(2, movie);
//          result = prepare.executeQuery();
//          if(result.next()){
//              double price = result.getDouble("PRICE");
//              double change = 0;
//              double payback = 0;
//              if(missing != 0){
//                  while(true){
//                      payback = input.InputDouble("Pay the extra: ");
//                      if(payback >= price){
//                          change = payback - price;
//                          break;
//                      }
//                  }
//              }
              prepare = connect.prepareStatement(queryMovie);
              prepare.setDouble(1, price);
              prepare.setDouble(2, payback);
              prepare.setDouble(3, change);
              prepare.setString(4, "Done");
              prepare.setString(5, username);
              prepare.setString(6, movie);
              prepare.executeUpdate();
              
              prepare = connect.prepareStatement("UPDATE Movie SET availability = ? WHERE title = ?");
              prepare.setInt(1, 1);
              prepare.setString(2, movie);
              prepare.executeUpdate();
              
        }catch(SQLException e){
            System.out.println("Connect error: " + e.getMessage());
        }
    }
    
    public String checkMovie(){
      String queryMovie = "SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?";
      String movies = input.InputString("Please enter the movie you want to return: ");
      try{
          prepare = connect.prepareStatement(queryMovie);
          prepare.setString(1, username);
          prepare.setString(2, movies);
          result = prepare.executeQuery();
          if(result.next()){
              prepare = connect.prepareStatement("SELECT RETURN_DATE FROM CUSTOMER_INFO WHERE RETURN_DATE = ?");
              prepare.setString(1, "Done");
              result = prepare.executeQuery();
              if(!result.next()){
                  System.out.println("Your movie has been paid.");
                  return "Unavailable";
              }else
                  return movies;
          }else{
              System.out.println("Your chosen movie is unavailable");
              return "Unavailable";
          }
          
      }catch(SQLException e){
          System.out.println("Connect error" + e.getMessage());
      }
        return movies;
    }
}
