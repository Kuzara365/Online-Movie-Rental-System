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
            MenuMovie menu = new MenuMovie();
            menu.showMovie();
            int choose = input.InputInteger("1. Rent a movie.\n2. History.\n3. Return movie. \n4. Delete account \n5. Logout.\nPlease choose: ");
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
                    System.out.println("3. Return movie");
                    ReturnMovie back = new ReturnMovie(customer);
                    back.MainTOReturn();
                    break;
                    
                case 4:
                    System.out.println("4. Delete account");
                    Delete delete = new Delete();
                    delete.delete(customer);
                    break;
                            
                case 5:
                    System.out.println("Logout");
                    option = false;
                    break;
                    
                default:
                    System.out.println("Please choose from 1 to 5.");
            }
        }
    }
}