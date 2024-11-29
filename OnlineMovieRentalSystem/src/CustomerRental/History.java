package CustomerRental;

import JDBC.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History {
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;

    public void history(String username) throws SQLException {
        String query = "SELECT * FROM History WHERE username = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                printHistory(username, query);
            } else {
                System.out.println("Your history is empty.\n");
            }
        } catch (SQLException e) {
            System.out.println("Can't connect: " + e.getMessage());
        } finally {
            if (prepare != null) {
                prepare.close();
            }
        }
    }
    
    public void printHistory(String customer, String query) throws SQLException{
        prepare = connect.prepareStatement(query);
        try{
              prepare = connect.prepareStatement(query);
            prepare.setString(1, customer);
            ResultSet result = prepare.executeQuery();
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-10s|%-10s|%-10s|%-11s|%-14s|%-10s|%-11s|%n", 
                              "Customer", "Movie", "Rental Date", "Return Date", "Price", "Payback", "Change", "Extra Price", "Extra Payback", "Change", "Total Price");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            int check = 0;
            while (result.next()) {
                String user = result.getString("username");
                String movie = result.getString("title");
                String rentalDate = result.getString("rentalDate");
                String returnDate = result.getString("ReturnDate");
                double price = result.getDouble("price");
                double payback = result.getDouble("payback");
                double change = result.getDouble("paychange");
                double extraPrice = result.getDouble("extra");
                double extra = result.getDouble("extrapay");
                double extrachange = result.getDouble("extrachange");
                double total = result.getDouble("total");

                System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-10.2f|%-10.2f|%-10.2f|%-11.2f|%-14.2f|%-10.2f|%-11.2f|%n", 
                                  user, 
                                  movie, 
                                  rentalDate, 
                                  returnDate, 
                                  price, 
                                  payback, 
                                  change,
                                  extraPrice,
                                  extra,
                                  extrachange,
                                  total);
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
             check++;
            }
            if(check < 1)
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        }catch(SQLException e){
            System.out.println("Connect error");
        }
    }
}
