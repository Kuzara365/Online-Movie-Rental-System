/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import JDBC.ConnectJDBC;
import Movies.inputHelper;

/**
 *
 * @author ngoct
 */
public class Main {

    public static void main(String[] args) {
        ConnectJDBC.init();
        Menu menu = Menu.getInstance();
        
        int choice = 0;
        do {
            System.out.println("\n=================== Main Menu ===================");
            System.out.println("1. Movie Management");
            System.out.println("2. Category Management");
            System.out.println("0. Exit");
            choice = inputHelper.readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        menu.MovieMenu();
                        break;

                    case 2:
                        menu.CategoryMenu();
                        break;

                    case 0:
                        System.out.println("See youu!!");
                        return;

                    default:
                        System.out.println("Invalid input. Try again 0..2!!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (true);

    }
}
