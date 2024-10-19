package test.movierental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToRental {
    private Connection connection;

    public ToRental(Connection connection) {
        this.connection = connection;
    }

    public void rentMovie(CustomerRental rent) {
        String sqlCommand = "INSERT INTO Rentals (user_id, movie_id, rental_date, return_date, charges, overdue_fine, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepare = connection.prepareStatement(sqlCommand)) {
            prepare.setInt(1, rent.getUserId());
            prepare.setInt(2, rent.getMovieId());
            prepare.setDate(3, rent.getRentalDate());
            prepare.setDate(4, rent.getReturnDate());
            prepare.setDouble(5, rent.getCharges());
            prepare.setDouble(6, rent.getOverdueFine());
            prepare.setString(7, rent.getStatus());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
