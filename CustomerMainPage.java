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
            int choose = input.InputInteger("1. Rent a movie.\n2. History.\n3. Search movie. \n4. Return movie. \n5. Delete account. \n6. Update account. \n7. Logout.\nPlease choose: ");
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
                    Search search = new Search();
                    search.search();
                    System.out.println("");
                    break;
                    
                case 4:
                    System.out.println("4. Return movie");
                    ReturnMovie back = new ReturnMovie(customer);
                    back.MainTOReturn();
                    break;
                    
                case 5:
                    System.out.println("5. Delete account");
                    Delete delete = new Delete();
                    delete.delete(customer);
                    break;
                    
                case 6:
                    System.out.println("6. Update account");
                    Update update = new Update(customer);
                    update.updatePage();
                    break;
                            
                case 7:
                    System.out.println("7. Logout");
                    option = false;
                    break;
                    
                default:
                    System.out.println("Please choose from 1 to 7.");
            }
        }
    }
}