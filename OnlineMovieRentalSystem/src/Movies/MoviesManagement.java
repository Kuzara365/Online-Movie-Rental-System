/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movies;

import Category.Category;
import MovieCategories.MovieCategoriesManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    System.out.println("+------------+----------------------+---------------------------+--------+-----------------+------------+------------------+");
        System.out.println(String.format("| %-10s | %-20s | %-25s | %-6s | %-15s | %-10s | %-16s |\n",
                "Movie ID", "Title", "Description", "Rating", "Availability", "Rent Price", "Year of Release"));
    System.out.println("+------------+----------------------+---------------------------+--------+-----------------+------------+------------------+");
    }

    void printFooter() {
    System.out.println("+------------+----------------------+---------------------------+--------+-----------------+------------+------------------+");
    }

    //add
    public void insertMovie(Movie m, List<Integer> categoryIDs) {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("INSERT INTO "
                    + "Movie(title, description, rating, availability, rental_price, year_of_release) "
                    + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getDescription());
            ps.setFloat(3, m.getRating());
            ps.setBoolean(4, m.isAvailability());
            ps.setDouble(5, m.getRentalPrice());
            ps.setInt(6, m.getYearOfRelease());

            int count = ps.executeUpdate();
            if (count > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                int movieID = 0;
                if (rs.next()) {
                    movieID = rs.getInt(1);
                }

                if (movieID > 0 && categoryIDs != null && !categoryIDs.isEmpty()) {
                    PreparedStatement categoryPS = connect.prepareStatement("INSERT INTO MovieCategory(movie_id, category_id) VALUES (?, ?)");
                    for (int categoryID : categoryIDs) {
                        categoryPS.setInt(1, movieID);
                        categoryPS.setInt(2, categoryID);
                        categoryPS.addBatch();
                    }
                    int[] querys = categoryPS.executeBatch();
                    System.out.println("Added " + querys.length + "Categories to movie");

                } else {
                    System.out.println("Add Movie Successfully");
                    System.out.println("But add Categories to Movie FAILED !");
                }

                System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                //show
                MovieCategoriesManagement mcm = MovieCategoriesManagement.getInstance();
                mcm.showMovieCategory();
            } else {
                System.out.println("Add Movie FAILED !!");
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
            PreparedStatement psInput = connection.prepareStatement("SELECT * FROM Movie WHERE movie_id = ?");
            psInput.setInt(1, input);
            ResultSet rs = psInput.executeQuery();

            if (rs.next()) {
                //get old date
                String oldTitle = rs.getString("title");
                String oldDescription = rs.getString("description");
                float oldRating = rs.getFloat("rating");
                boolean oldAvail = rs.getBoolean("availability");
                String oldAvailDisplay = oldAvail ? "Available" : "Not Available";
                double oldRentalPrice = rs.getDouble("rental_price");
                int oldYearOfRelease = rs.getInt("year_of_release");

                String title = inputHelper.readStringAllowEnter("Enter Title [" + oldTitle + "]: ", oldTitle);
                String description = inputHelper.readStringAllowEnter("Enter Description [" + oldDescription + "]: ", oldDescription);
                float rating = inputHelper.readFloatAllowEnter("Enter Rating [" + oldRating + "]: ", oldRating);
                boolean avail = inputHelper.readBooleanAllowEnter("Enter Status [" + oldAvailDisplay + "]\n"
                        + "1. Available\n"
                        + "0. Not Available\n"
                        + "Choice: ", oldAvail);
                double rentalPrice = inputHelper.readDoubleAllowEnter("Enter Rental's price [" + oldRentalPrice + "]: ", oldRentalPrice);
                int yearOfRelease = inputHelper.readIntAllowEnter("Enter Year Of Release [" + oldYearOfRelease + "]: ", oldYearOfRelease);

                PreparedStatement psUpdate = connection.prepareStatement("UPDATE Movie "
                        + "SET title = ?, description = ?, "
                        + "rating = ?, availability = ?, rental_price = ?, "
                        + "year_of_release = ? WHERE movie_id = ?");

                psUpdate.setString(1, title);
                psUpdate.setString(2, description);
                psUpdate.setFloat(3, rating);
                psUpdate.setBoolean(4, avail);
                psUpdate.setDouble(5, rentalPrice);
                psUpdate.setInt(6, yearOfRelease);
                psUpdate.setInt(7, input);

                int count = psUpdate.executeUpdate();
                if (count > 0) {
                    System.out.println("Update Successfully!!");
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--***-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*");

                    showAll();
                } else {
                    System.out.println("No changes were made!");
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
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Movie WHERE movie_id = ?");
            ps.setInt(1, movieId);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Delete Successfully !!");
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--***-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*");
                showAll();
            } else {
                System.out.println("Delete failed!!. Movie Id [" + movieId + "] not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //search title, description
    public void searchMovie() {
        try {
            String keyword = inputHelper.readString("Enter keyword: ");
            Connection connection = JDBC.ConnectJDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Movie WHERE title LIKE ? OR description LIKE ?");

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                printHeader();
                do {
                    System.out.printf("| %-10d | %-20s | %-25s | %-6.1f | %-15s | %-10.2f | %-16d |\n",
                            rs.getInt("movie_id"), rs.getString("title"), rs.getString("description"),
                            rs.getFloat("rating"), rs.getBoolean("availability") ? "Available" : "Not Available",
                            rs.getDouble("rental_price"), rs.getInt("year_of_release")
                    );
                    printFooter();
                } while (rs.next());
            } else {
                System.out.println("Not found!!");
            }
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
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie m = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getFloat("rating"),
                        rs.getBoolean("availability"),
                        rs.getDouble("rental_price"),
                        rs.getInt("year_of_release")
                );
                Movies.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Movies;
    }

    public void showAll() {
        List<Movie> movies = listMovie();

        if (movies == null || movies.isEmpty()) {
            System.out.println("EMPTY !!");
            return;
        }
        printHeader();
        for (Movie m : movies) {
            System.out.println(m.toString());
            printFooter();
        }

    }
}
