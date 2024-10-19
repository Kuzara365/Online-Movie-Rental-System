package test.movierental;

import java.sql.Date;

public class CustomerRental {
    private int rentalId;
    private int userId;
    private int movieId;
    private Date rentalDate;
    private Date returnDate;
    private double charges;
    private double overdueFine;
    private String status;

    public CustomerRental(int rentalId, int userId, int movieId, Date rentalDate, Date returnDate, double charges, double overdueFine, String status) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.movieId = movieId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.charges = charges;
        this.overdueFine = overdueFine;
        this.status = status;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public double getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(double overdueFine) {
        this.overdueFine = overdueFine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public boolean isOverdue() {
        return returnDate != null && returnDate.before(new Date(System.currentTimeMillis()));
    }
}
