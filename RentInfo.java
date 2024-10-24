package CustomerRental;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentInfo {
    protected String username;
    protected String movie;
    protected String rentalDate;
    protected String returnDate;
    protected double price;
    protected double Overdues;
    protected double pay;
    protected double change;
    
    public RentInfo(String username, String movie, double price){
        this.username = username;
        this.rentalDate = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.returnDate = "" + LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.movie = movie;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOverdues() {
        return Overdues;
    }

    public void setOverdues(double Overdues) {
        this.Overdues = Overdues;
    }

    public double getPayback() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
    
}