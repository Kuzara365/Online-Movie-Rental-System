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
        String query = "SELECT * FROM Movie";
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            if (result == null) {
                System.out.println("The list of movie is empty!!!");
            } else {
                System.out.println("Here is a list of all available movies: ");
                System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
                System.out.printf("|%-20s|%-30s|%-30s|%-20s|%-30s|%-20s|%-15s|\n",
                        "MovieID", "Title", "Description", "Rating", "Availability",
                        "Rental Price", "Year of Release");
                System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

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
                }
            }
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
