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
        String query = "SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                printHistory(username, query);
            } else {
                System.out.println("Your history is empty.");
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

            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-20s|%-30s|%-30s|%-30s|%-20s|%-20s|%-20s|%n", 
                              "Customer", "Movie", "Rental Date", "Return Date", "Price", "Payback", "Change");

            while (result.next()) {
                String user = result.getString("CUSTOMER");
                String movie = result.getString("MOVIE");
                String rentalDate = result.getString("RENTAL_DATE");
                String returnDate = result.getString("RETURN_DATE");
                double price = result.getDouble("PRICE");
                double payback = result.getDouble("PAYBACK");
                double change = result.getDouble("CHANGE");

                System.out.printf("|%-20s|%-50s|%-20s|%-20s|%-20.2f|%-20.2f|%-20.2f|%n", 
                                  user, 
                                  movie, 
                                  rentalDate, 
                                  returnDate, 
                                  price, 
                                  payback, 
                                  change);
            }

            System.out.println("+-----------------------------------------------------------------------------------------------------+");
        }catch(SQLException e){
            System.out.println("Connect error");
        }
    }
}
