/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class MoviesManagement {

    public static Scanner sc = new Scanner(System.in);

    public static MoviesManagement instance;

    public static MoviesManagement getInstance() {
        if (instance == null) {
            instance = new MoviesManagement();
        }
        return instance;
    }

    String table = "| %-10d | %-17s | %-14s | %-6.1f | %-12s | %-10.2f | %-10d | %-14d |\n";

    void printHeader() {
        System.out.println("+------------+-------------------+----------------+--------+--------------+------------+------------+----------------+");
        System.out.println("| Movie ID   | Title              | Description    | Rating | Availability | Rent Price | CategoryID | Year of Release |");
        System.out.println("+------------+-------------------+----------------+--------+--------------+------------+------------+----------------+");
    }

    void printFooter() {
        System.out.println("+------------+-------------------+----------------+--------+--------------+------------+------------+----------------+");
    }

    //1. add
    public Movie inputMovie() {
        int movieId = inputHelper.readInt("Enter Movie's ID: ");
        String title = inputHelper.readString("Enter Title: ");
        String description = inputHelper.readString("Enter Description: ");
        float rating = inputHelper.readFloat("Enter Rating: ");
        boolean avail = inputHelper.readBoolean("Enter Status:\n"
                + "1. True\n"
                + "0. False\n"
                + "Choice: ");
        double rentalPrice = inputHelper.readDouble("Enter Rental's price: ");
        int CategoryId = inputHelper.readInt("Enter Category's ID: ");
        int yearOfRelease = inputHelper.readInt("Enter Year Of Release: ");
        return new Movie(movieId, CategoryId, title, description, rating, avail, rentalPrice, yearOfRelease);
    }

    public void insertMovie(Movie m) {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("INSERT INTO TABLE Movies(movies_id, title, description, rating, availability, rental_price, category_id, year_of_release) VALUES (?, ?, ?, ?, ?, ?, ?");
            ps.setInt(0, m.getMovieID());
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getDescription());
            ps.setFloat(3, m.getRating());
            ps.setBoolean(4, m.isAvailability());
            ps.setInt(5, m.getCategoryID());
            ps.setInt(6, m.getYearOfRelease());

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Add Movie Successfully!!");
                System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                //show
            } else {
                System.out.println("Add FAILED !!");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
    
    

    //update
    //delete
    //search
    //show
    public List<Movie> listMovie(){
        List<Movie> Movies = new ArrayList<>();
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM Movies");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Movie m = new Movie(rs.getInt(table), 0, table, table, 0, true, 0, 0)
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
