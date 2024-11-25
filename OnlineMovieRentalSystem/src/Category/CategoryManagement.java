/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Category;

import MovieCategories.MovieCategoriesManagement;
import Movies.inputHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngoct
 */
public class CategoryManagement {

    public static CategoryManagement instance;

    public static CategoryManagement getInstance() {
        if (instance == null) {
            instance = new CategoryManagement();
        }
        return instance;
    }
    

    public boolean isCategoryValid(int categoryID) {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement psCheck = connect.prepareStatement("SELECT 1 FROM Category WHERE category_id = ?");
            psCheck.setInt(1, categoryID);
            ResultSet rs = psCheck.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public void addCategory(Category c) {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
//            String categoryName = inputHelper.readString("Enter Category: ");
            String checkSql = "SELECT 1 FROM Category WHERE category_name = ?";
            PreparedStatement psCheck = connect.prepareStatement(checkSql);
            psCheck.setString(1, c.getCategoryName());
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                System.out.println("Category [" + c.getCategoryName() + "] already exists.");
                return;
            }

            PreparedStatement ps = connect.prepareStatement("INSERT INTO Category(category_name) VALUES(?)");
            ps.setString(1, c.getCategoryName());

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Add successfully!!");
                System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                showCategory();
            } else {
                System.out.println("Add Category Failed!!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateCategory() {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            int catogoryID = inputHelper.readInt("Enter Category ID: ");
            PreparedStatement ps = connect.prepareStatement("UPDATE Category SET category_name = ? WHERE category_id = ?");
            ps.setInt(2, catogoryID);
            ps.setString(1, inputHelper.readString("Enter new Category: "));

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Update Successfully!!");
                System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                showCategory();
            } else {
                System.out.println("Update Failed !!");
                System.out.println("Not found by Category ID: " + catogoryID);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteCategory() {
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
                MovieCategoriesManagement mcm = MovieCategoriesManagement.getInstance();

            int categoryID = inputHelper.readInt("Enter category ID to delete: ");
            mcm.deleteMovieCategory(categoryID, "category_id");
            PreparedStatement ps = connect.prepareStatement("DELETE FROM Category WHERE category_id = ?");
            ps.setInt(1, categoryID);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Delete successfully!!");
                System.out.println("***--*-*-*-*--*-***-*-*-*-*-*-*-*-*--*---****-*-*-*-*-*-*-******--*-*-*--*--*-*-*--*-*-*-***-****-*--*****----*--**");
                showCategory();
            } else {
                System.out.println("DELETE FAILED!!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Category> ListCategory() {
        List<Category> categories = new ArrayList<>();
        Connection connect = JDBC.ConnectJDBC.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM Category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                categories.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return categories;
    }

    public void showCategory() {
        List<Category> categories = ListCategory();

        if (categories == null || categories.isEmpty()) {
            System.out.println("EMPTY !!");
            return;
        }

        System.out.printf("| %10s | %25s |\n", "ID", "Name");
        System.out.println("------------------------------------------");
        for (Category category : categories) {
            System.out.println(category.toString());
            System.out.println("------------------------------------------");
        }
    }

}
