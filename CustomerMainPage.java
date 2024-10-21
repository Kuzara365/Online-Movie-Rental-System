package CustomerRental;

import test.be2.InputMain;
public class CustomerMainPage {
    InputMain input = new InputMain();
    public void Page(String customer){
        System.out.printf("Welcome to the website, %s.\n", customer);
        boolean option = true;
        while(option){
            int choose = input.InputInteger("1. Rent a movie.\n2. History.\n3. Show movie list.\n4. Logout.\nPlease choose: ");
            switch(choose){
                case 1:
                    ToRent rent = new ToRent();
                    rent.RentMovie(customer);
                    break;
                    
                case 2:
                    
                    break;
                    
                case 3:
                    
                    break;
                            
                case 4: default:
                    System.out.println("Logout");
                    option = false;
            }
        }
    }
}
