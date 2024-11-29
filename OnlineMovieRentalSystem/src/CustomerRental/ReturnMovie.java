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
           int missing = Late(movie);
           if(missing == -1){
               return;
           }
            Returning(movie, missing);
        }
    }
    
public int Late(String movie) {
    try {
        String today = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
            + LocalDateTime.now().format(DateTimeFormatter.ofPattern(" HH:mm:ss"));
        
        prepare = connect.prepareStatement("SELECT * FROM History WHERE username = ? AND title = ?");
        prepare.setString(1, username);
        prepare.setString(2, movie);
        result = prepare.executeQuery();
        int count = 0;
        while(result.next()) {
            String returnDate = result.getString("returnDate");
            if (!returnDate.equals("Done")) {
                LocalDateTime now = LocalDateTime.parse(today, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime returndate = LocalDateTime.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (now.isAfter(returndate)) {
                    return (int) ChronoUnit.DAYS.between(returndate.toLocalDate(), now.toLocalDate());
                }else{
                    return 0;
                }
            } else {
                count++;
            }
        }
        if(count > 0){
            System.out.println("You might already returned the movie or you haven't rented this movie yet.");
                return -1;
        }
    } catch (SQLException e) {
        System.out.println("Connection error: " + e.getMessage());
    }
    return 0;
}
    
    public void Returning(String movie, int missing){
        String queryMovie = "UPDATE History SET returnDate = ?, extra = extra + ?, extrapay = extrapay + ?, extrachange = extrachange + ?, total = total + ? WHERE username = ? AND title = ? AND returnDate != ?";
        double price = 5 * missing;
        double payback = 0;
        double change = 0;
        if(price == 0){
            System.out.println("Thanks for returning on time.");
        }else{
         while(payback < price){
             System.out.println("Your extra payment is: " + price);
             payback = input.InputDouble("Please enter number for paying: ");
         }
         change = Math.abs(price - payback);
         System.out.printf("Your change is: %.2f \n", change);
        }
        try{
              prepare = connect.prepareStatement(queryMovie);
              prepare.setString(1, "Done");
              prepare.setDouble(2, price);
              prepare.setDouble(3, payback);
              prepare.setDouble(4, change);
              prepare.setDouble(5, price);
              prepare.setString(6, username);
              prepare.setString(7, movie);
              prepare.setString(8, "Done");
              
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
      String queryMovie = "SELECT * FROM History WHERE username = ? AND title = ?";
      String movies = input.InputString("Please enter the movie you want to return: ");
      try{
          prepare = connect.prepareStatement(queryMovie);
          prepare.setString(1, username);
          prepare.setString(2, movies);
          result = prepare.executeQuery();
          if(result.next()){
              prepare = connect.prepareStatement("SELECT returnDate FROM History WHERE returnDate = ? AND username = ? AND title = ?");
              prepare.setString(1, "Done");
              prepare.setString(2, username);
              prepare.setString(3, movies);
              result = prepare.executeQuery();
              if(result == null){
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
