package test.movierental;

import MovieManagement.MoviesManagement;
import MovieManagement.Movie;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        ToRental rental = new ToRental(connection);
        Inputter input = new Inputter();
        MoviesManagement moviesManagement = MoviesManagement.getInstance();

        int userId = input.inputInteger("Please enter your user ID: ");
        int movieId = input.inputInteger("Please enter your movie ID: ");

        if(moviesManagement.) {
            System.out.println("The movie is currently unavailable for rent or does not exist.");
            return;
        }

        Date rentalDate = Date.valueOf(input.getCurrentFormattedDate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(rentalDate);
        cal.add(Calendar.MONTH, 1);
        Date maxReturnDate = new Date(cal.getTimeInMillis());
        Date returnDate = input.inputDate("Please enter the return date (yyyy-mm-dd): ");

        if (returnDate.after(maxReturnDate)) {
            System.out.println("Return date must be within one month from the rental date.");
            return;
        }

        //double charges = ;
        double overdueFine = input.inputDouble("Please enter the overdue fine: ");
        String status = "rented";

        CustomerRental customerRental = new CustomerRental(0, userId, movieId, rentalDate, returnDate, charges, overdueFine, status);
        rental.rentMovie(customerRental);

        input.close();
    }
}
