package CustomerRental;

import Login.InputMain;
import Movies.MoviesManagement;
import java.sql.SQLException;

public class CustomerMainPage {
    InputMain input = new InputMain();
    MoviesManagement movie = new MoviesManagement();
    
    public void Page(String customer) throws SQLException{
        System.out.printf("Welcome to the website, %s.\n", customer);
        boolean option = true;
        while(option){
            System.out.println("Here is a list of all available movies: ");
            MenuMovie menu = new MenuMovie();
            menu.showMovie();
            int choose = input.InputInteger("1. Rent a movie.\n2. History.\n3. Logout.\nPlease choose: ");
            switch(choose){
                case 1:
                    System.out.println("1. Rent a movie.");
                    ToRent rent = new ToRent();
                    rent.RentMovie(customer);
                    break;
                    
                case 2:
                    System.out.println("2. History");
                    History history = new History();
                    history.history(customer);
                    break;
                            
                case 3:
                    System.out.println("Logout");
                    option = false;
                    break;
                    
                default:
                    System.out.println("Please choose from 1 to 3.");
            }
        }
    }
}