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
            double pay = Payment(movie);
            int missing = Late(movie);
            Returning(movie, pay, missing);
        }
    }
    
    public int Late(String movie){
        int count = 0;
        try{
            String today = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            prepare = connect.prepareStatement("SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?");
            prepare.setString(1, username);
            prepare.setString(2, movie);
            result = prepare.executeQuery();
            if(result.next()){
                String returnDate = result.getString("RETURN_DATE");
                LocalDateTime now = LocalDateTime.parse(today, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime returndate = LocalDateTime.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if(now.isAfter(returndate)){
                    count = (int) ChronoUnit.DAYS.between(returndate.toLocalDate(), now.toLocalDate());
                }
            }
        }catch(SQLException e){
            System.out.println("Connect error: " + e.getMessage());
        }
        return count;
    }
    
    public double Payment(String movie){
        try{
            prepare = connect.prepareStatement("SELECT PRICE FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?");
            prepare.setString(1, username);
            prepare.setString(2, movie);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getDouble("PRICE");
            }
        }catch(SQLException e){
            System.out.println("Connect error: " + e.getMessage());
        }
        return 0;
    }
    
    public void Returning(String movie, double pay, int missing){
        String queryMovie = "SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ? AND MOVIE = ?";
        
        try{
          prepare = connect.prepareStatement(queryMovie);
          prepare.setString(1, username);
          prepare.setString(2, movie);
          result = prepare.executeQuery();
          if(result.next()){
              double price = result.getDouble("PRICE");
              double change = 0;
              double payback = 0;
              if(missing != 0){
                  while(true){
                      payback = input.InputDouble("Pay the extra: ");
                      if(payback >= price){
                          change = payback - price;
                          break;
                      }
                  }
              }
              prepare = connect.prepareStatement("UPDATE CUSTOMER_INFO SET PRICE = ?, PAYBACK = ?, CHANGE = ?, RETURN_DATE = ? WHERE CUSTOMER = ? AND MOVIE = ?");
              prepare.setDouble(1, price);
              prepare.setDouble(2, payback);
              prepare.setDouble(3, change);
              prepare.setString(4, "Done");
              prepare.setString(5, username);
              prepare.setString(6, movie);
              prepare.executeQuery();
              
              prepare = connect.prepareStatement("UPDATE Movies SET availability = ? WHERE title = ?");
              prepare.setBigDecimal(1, BigDecimal.ONE);
              prepare.setString(2, movie);
              prepare.executeQuery();
              

          }else{
              System.out.println("Fail to return.");
          }
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
              prepare = connect.prepareStatement("SELECT RETURN_DATE FROM CUSTOMER_INFO LIKE RETUR_DATE LIKE ?");
              prepare.setString(1, "%Done%");
              result = prepare.executeQuery();
              if(result.next()){
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
