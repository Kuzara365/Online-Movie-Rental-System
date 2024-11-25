/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Category.Category;
import Category.CategoryManagement;
import MovieCategories.MovieCategoriesManagement;
import MovieCategories.MovieCategory;
import Movies.Movie;
import Movies.MoviesManagement;
import Movies.inputHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    public static Menu instance;

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    MovieCategoriesManagement mcm = MovieCategoriesManagement.getInstance();
    CategoryManagement cm = CategoryManagement.getInstance();
    MoviesManagement mm = MoviesManagement.getInstance();

    public void MovieMenu() {

        int choice = 0;
        do {
            System.out.println("==========================================");
            System.out.println("             Movie Management             ");
            System.out.println("==========================================");
            System.out.println("1. Add Movie");
            System.out.println("2. View All Movie");
//            System.out.println("3. Search Movie");
            System.out.println("3. Update Movie Infor");
            System.out.println("4. Remove Movie");
            System.out.println("0. Exit");
            System.out.println("==========================================");
            choice = inputHelper.readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 0:
                        System.out.println("See youu !!");
                        return;

                    case 1:
                        Movie m = new Movie();
                        Movie input = m.inputMovie();
                        List<Integer> categoryIDs = new ArrayList<>();
                        while (true) {
                            cm.showCategory();
                            int categoryid = inputHelper.readInt("Enter Category ID to add (or 0 to exit): ");
                            if (categoryid == 0) {
                                break;
                            }
                            categoryIDs.add(categoryid);

                        }
                        mm.insertMovie(input, categoryIDs);
                        break;

                    case 2:
                        mcm.showMovieCategory();
                        break;

//                    case 3:
//                        mm.searchMovie();
//                        break;

                    case 3:
                        mm.updateMovie();
                        break;

                    case 4:
                        mm.removeMovie();
                        break;
                        
                    default:
                        System.out.println("Enter 0..4");
                        break;

                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }

        } while (choice != 0);
    }

    public void CategoryMenu() {
        int choice = 0;
        while (true) {
            System.out.println("=====================================");
            System.out.println("         Category Management         ");
            System.out.println("=====================================");
            System.out.println("0. Exit");
            System.out.println("1. Add Category");
            System.out.println("2. View All Category");
            System.out.println("3. Update Category");
            System.out.println("4. Remove Category");
            System.out.println("=====================================");
            choice = inputHelper.readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 0:
                        System.out.println("See youu!!");
                        return;

                    case 1:
                        Category c = new Category();
                        cm.addCategory(c.input());
                        break;

                    case 2:
                        cm.showCategory();
                        break;

                    case 3:
                        cm.updateCategory();
                        break;

                    case 4:
                        cm.deleteCategory();
                        break;

                    default:
                        System.out.println("Only Input 0..4");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    
//    public void categoryEdit() {
//        int choice = 0;
//        while (true) {
//            System.out.println("0. Exit");
//            System.out.println("1. Add more categories to Movie");
//            System.out.println("2. Delete Category to a Movie");
//            choice = inputHelper.readInt("Enter your choice: ");
//
//            try {
//                switch (choice) {
//                    case 0:
//                        System.out.println("See youu!!");
//                        return;
//
//                    case 1:
//                        mcm.showMovieCategory();
//                        int moiveID = inputHelper.readInt("Enter Movie ID: ");
//                        List<Integer> categoryIDs = new ArrayList<>();
//                        while (true) {
//                            cm.showCategory();
//                            int categoryid = inputHelper.readInt("Enter Category ID to add (or 0 to exit): ");
//                            if (categoryid == 0) {
//                                break;
//                            }
//                            categoryIDs.add(categoryid);
//                        }
//                        mcm.addMovieCategory(moiveID, categoryIDs);
//                        break;
//
//                    case 2:
//                        mcm.deleteMovieCategory(inputHelper.readInt("Enter Category ID: "), "");
//                        break;
//
//                    default:
//                        System.out.println("Only Input 0..2");
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//
//        }
//    }
}
