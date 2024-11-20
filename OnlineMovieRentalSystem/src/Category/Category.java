/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Category;

import Movies.inputHelper;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Category {

    private int categoryID;
    private String categoryName;

    static Scanner sc = new Scanner(System.in);

    public Category(){
    }
    
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category input() {
        String name = inputHelper.readString("Enter Category: ");
        return new Category(name);
    }
    
    @Override
    public String toString(){
        return String.format("| %10d | %25s |\n", categoryID, categoryName);
    }
}
