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
    public void updateMovie() {
        Connection connection = JDBC.ConnectJDBC.getConnection();
        try {
            int input = inputHelper.readInt("Enter Movie's ID: ");
            inputMovie();
            PreparedStatement ps = connection.prepareStatement("UPDATE Movies "
                    + "SET movies_id = ? and title = ? description = ? "
                    + "rating = ? availability = ? rental_price = ? "
                    + "category_id = ? year_of_release = ? where movies_id = ?");
            ps.setInt(9, input);
            
        
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            

        }
    }
    
    //delete
    public void removeMovie() {
        try {
            Connection connection = JDBC.ConnectJDBC.getConnection();
            int movieId = inputHelper.readInt("Enter Movie's ID: ");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Movies WHERE movies_id = ?");
            ps.setInt(1, movieId);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Delete Successfully !!");
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--***-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*");
                showAll();
            } else {
                System.out.println("Delete failed!!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //search
    public int checkId(String table){
        Connection connection = JDBC.ConnectJDBC.getConnection();
        try {
            int id = inputHelper.readInt("Enter ID " + table + ": ");
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM " + table + "WHERE id = ?");
            
            
        } catch (SQLException e) {
        }
        return 0;
    }
    public void searchMovie() {
        try {
            Connection connection = JDBC.ConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement("");
        } catch (Exception e) {
        }
    }

    //show
    public List<Movie> listMovie() {
        List<Movie> Movies = new ArrayList<>();
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM Movies");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie m = new Movie(rs.getInt("movies_id"),
                        rs.getInt("category_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getFloat("rating"),
                        rs.getBoolean("availability"),
                        rs.getDouble("rental_price"),
                        rs.getInt("year_of_release")
                );
                Movies.add(m);
            }
            return Movies;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void showAll() {
        List<Movie> movies = listMovie();
        if (!movies.isEmpty()) {
            printHeader();
            for (Movie m : movies) {
                System.out.println(m.toString());
                printFooter();
            }
        } else {
            System.out.println("EMPTY !!");
        }
    }
}


