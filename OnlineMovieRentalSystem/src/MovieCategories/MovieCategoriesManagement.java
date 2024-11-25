/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieCategories;

import Category.CategoryManagement;
import Movies.inputHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ngoct
 */
public class MovieCategoriesManagement {

    public static MovieCategoriesManagement instance;

    public static MovieCategoriesManagement getInstance() {
        if (instance == null) {
            instance = new MovieCategoriesManagement();
        }
        return instance;
    }

    public void addMovieCategory(int id, List<Integer> categoryIDs) {
        CategoryManagement cm = CategoryManagement.getInstance();
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            if (categoryIDs != null && !categoryIDs.isEmpty()) {
                PreparedStatement categoryPS = connect.prepareStatement("INSERT INTO MovieCategory(movie_id, category_id) VALUES (?, ?)");
                for (int categoryID : categoryIDs) {
                    if (cm.isCategoryValid(categoryID)) {
                        categoryPS.setInt(1, id);
                        categoryPS.setInt(2, categoryID);
                        categoryPS.addBatch();
                    } else {
                        System.out.println("Category ID [" + categoryID + "] does not exist!!");
                    }

                }
                int[] querys = categoryPS.executeBatch();
                System.out.println("Added " + querys.length + " Categories to movie");

            } else {
                System.out.println("Add Category failed !!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteMovieCategory(int id, String col) {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        do {
            try {
//                int CategoryId = inputHelper.readInt("Enter Movie ID to delete: ");
                PreparedStatement ps = connect.prepareStatement("DELETE FROM MovieCategory WHERE " + col + " = ?");
                ps.setInt(1, id);

                int count = ps.executeUpdate();
                if (count > 0) {
                    System.out.println("Delete Successfully!!");
                    System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                    break;
                } else {
                    System.out.println("Not found ID [" + id + "]");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (true);

    }

    public void showMovieCategory() {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT \n"
                    + "    M.movie_id, \n"
                    + "    M.title, \n"
                    + "    CAST(M.description AS NVARCHAR(MAX)) AS description, \n"
                    + "    M.rating, \n"
                    + "    M.availability, \n"
                    + "    M.rental_price, \n"
                    + "    M.year_of_release,\n"
                    + "    STRING_AGG(C.category_name, ', ') AS category_names\n"
                    + "FROM \n"
                    + "    Movie AS M\n"
                    + "LEFT JOIN \n"
                    + "    MovieCategory AS MC ON M.movie_id = MC.movie_id\n"
                    + "INNER JOIN \n"
                    + "    Category AS C ON MC.category_id = C.category_id\n"
                    + "GROUP BY \n"
                    + "    M.movie_id, M.title, CAST(M.description AS NVARCHAR(MAX)), M.rating, \n"
                    + "    M.availability, M.rental_price, M.year_of_release;");
            ResultSet rs = ps.executeQuery();

            System.out.printf("| %-10s | %-20s | %-30s | %-6s | %-15s | %-10s | %-16s | %-30s |\n",
                    "ID", "Title", "Description", "Rate", "Availability", "Price", "Year", "Categories");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("| %-10d | %-20s | %-30s | %-6.1f | %-15s | %-10.2f | %-16d | %-30s |\n",
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getFloat("rating"),
                        rs.getBoolean("availability") ? "Available" : "Not Available",
                        rs.getDouble("rental_price"),
                        rs.getInt("year_of_release"),
                        rs.getString("category_names"));
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

            if (!found) {
                System.out.println("EMPTY!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public void editMovieCategory() {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            showMovieCategory();
            int MovieID = inputHelper.readInt("Enter Movie ID to edit: ");
            PreparedStatement psDelete = connect.prepareStatement("DELETE FROM MovieCategory WHERE movie_id = ?");
            psDelete.setInt(1, MovieID);
            psDelete.execute();
            int ok = 1;
            while (ok != 0) {
                PreparedStatement psAdd = connect.prepareStatement("INSERT INTO MovieCategory(movie_id, category_id) VALUES (" + MovieID + ", ?)");
                CategoryManagement cm = CategoryManagement.getInstance();
                cm.showCategory();
                psAdd.setInt(1, inputHelper.readInt("Enter Category ID: "));
                psAdd.execute();
                System.out.println("Edit Movie Category:");
                System.out.println("1. Continue Add");
                System.out.println("0. exit");
                ok = inputHelper.readInt("Enter choice: ");
                if (ok == 1) {
                    continue;
                } else if (ok == 0) {
                    break;
                }

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
