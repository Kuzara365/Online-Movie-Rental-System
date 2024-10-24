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
        System.out.println(String.format("| %-10s | %-17s | %-14s | %-6s | %-12s | %-10s | %-10s | %-14s |",
                "Movie ID", "Title", "Description", "Rating", "Availability", "Rent Price", "CategoryID", "Year of Release"));
        System.out.println("+------------+-------------------+----------------+--------+--------------+------------+------------+----------------+");
    }

    void printFooter() {
        System.out.println("+------------+-------------------+----------------+--------+--------------+------------+------------+----------------+");
    }

    //1. add
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
            PreparedStatement psInput = connection.prepareStatement("SELECT * FROM Movies WHERE movies_id = ?");
            psInput.setInt(1, input);
            ResultSet rs = psInput.executeQuery();

            if (rs.next()) {
                //get old date
                String oldTitle = rs.getString("title");
                String oldDescription = rs.getString("description");
                float oldRating = rs.getFloat("rating");
                boolean oldAvail = rs.getBoolean("availability");
                double oldRentalPrice = rs.getDouble("rental_price");
                int oldCategoryId = rs.getInt("category_id");
                int oldYearOfRelease = rs.getInt("year_of_release");

                String title = inputHelper.readStringAllowEnter("Enter Title [" + oldTitle + "]): ", oldTitle);
                String description = inputHelper.readStringAllowEnter("Enter Description [" + oldDescription + "]): ", oldDescription);
                float rating = inputHelper.readFloatAllowEnter("Enter Rating [" + oldRating + "]): ", oldRating);
                boolean avail = inputHelper.readBooleanAllowEnter("Enter Status [" + oldAvail + "]\n"
                        + "1. True\n"
                        + "0. False\n"
                        + "Choice: ", oldAvail);
                double rentalPrice = inputHelper.readDoubleAllowEnter("Enter Rental's price [" + oldRentalPrice + "]): ", oldRentalPrice);
                int categoryId = inputHelper.readIntAllowEnter("Enter Category's ID [" + oldCategoryId + "]): ", oldCategoryId);
                int yearOfRelease = inputHelper.readIntAllowEnter("Enter Year Of Release [" + oldYearOfRelease + "]): ", oldYearOfRelease);

                PreparedStatement psUpdate = connection.prepareStatement("UPDATE Movies "
                        + "SET title = ?, description = ?, "
                        + "rating = ?, availability = ?, rental_price = ?, "
                        + "category_id = ?, year_of_release = ? where movies_id = ?");

                psUpdate.setString(1, title);
                psUpdate.setString(2, description);
                psUpdate.setFloat(3, rating);
                psUpdate.setBoolean(4, avail);
                psUpdate.setDouble(5, rentalPrice);
                psUpdate.setInt(6, categoryId);
                psUpdate.setInt(7, yearOfRelease);
                psUpdate.setInt(8, input);

                int count = psUpdate.executeUpdate();
                if (count > 0) {
                    System.out.println("Update Successfully!!");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--***-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*");

                    showAll();
                } else {
                    System.out.println("Update failed!!");
                }

            } else {
                System.out.println("Movie Id [" + input + "] not found.");
            }

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

//    public int checkId(String table) {
//        Connection connection = JDBC.ConnectJDBC.getConnection();
//        try {
//            int id = inputHelper.readInt("Enter ID " + table + ": ");
//            PreparedStatement ps = connection.prepareStatement("SELECT COUNT (*) FROM " + table + "WHERE id = ?");
//
//        } catch (SQLException e) {
//        }
//        return 0;
//    }
    //search 
    public void searchMovie() {
        try {
            String keyword = inputHelper.readString("Enter keyword: ");
            Connection connection = JDBC.ConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM"
                    + "Movies WHERE title LIKE ?"
                    + " OR description LIKE ?"
                    + " OR rating = ?"
                    + " OR availability = ?"
                    + " OR rental_price = ?"
                    + " OR year_of_release = ?");

            float f = Float.parseFloat(keyword);
            boolean b = Boolean.parseBoolean(keyword);
            double d = Double.parseDouble(keyword);
            int i = Integer.parseInt(keyword);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setFloat(3, f);
            ps.setBoolean(4, b);
            ps.setDouble(5, d);
            ps.setInt(6, i);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                printHeader();
                do {
                    System.out.println(rs.toString());
                    printFooter();
                } while (rs.next());
            } else {
                System.out.println("Not found!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!!");
        } catch (SQLException se) {
            System.out.println("SQL Error: " + se.getMessage());
        } catch (Exception ex) {
            System.out.println("Error..." + ex.getMessage());
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
