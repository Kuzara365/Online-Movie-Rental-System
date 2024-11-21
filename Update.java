package CustomerRental;

import JDBC.ConnectJDBC;
import Login.InputMain;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author HuyDepZai
 */
public class Update {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    String customer = "";
    Scanner sc = new Scanner(System.in);
    
    public Update(String customer){
        this.customer = customer;
        try{
          prepare = connect.prepareStatement("INSERT INTO Information(CUSTOMER) VALUES (?)");
          prepare.setString(1, customer);
          prepare.executeUpdate();
        }catch(SQLException e){
            System.out.println("");
        }
    }
    
    public void updatePage(){
        String query = "SELECT * FROM Information";
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            
            System.out.println("Your information: ");
            System.out.println("+----------------------------------------------------------------------------------+");
            System.out.printf("|%-20s|%-30s|%-30s|\n", 
                              "Customer", "Email", "Introduction");
            System.out.println("+----------------------------------------------------------------------------------+");
            int count = 0;
            while (result.next()) {
                String customer = result.getString("CUSTOMER");
                String email = result.getString("EMAIL");
                String introduction = result.getString("INTRODUCE");

                System.out.printf("|%-20s|%-30s|%-30s|\n", 
                                  customer, 
                                  email, 
                                  introduction);
            System.out.println("+----------------------------------------------------------------------------------+");
            count++;
            }
            
                    boolean loop = true;
        while(loop){
        String haveAccount = input.InputString("Do you want to update?[Y/N]: ").toUpperCase();
        switch(haveAccount){
            case "Y":
                UpdateInfo();
                loop = false;
                break;
                
            case "N":
                loop = false;
                break;
                
            default:
                System.out.println("Please only enter 'Y' or 'N'");
        }
       }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
    
    public void UpdateInfo(){
        String email = "";
        while(true){
        System.out.print("Enter email: ");
        email = sc.nextLine();
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            break;
        }else{
            System.out.println("Please enter email format.");
        }
       }
        
        System.out.print("Introduce yourself: ");
        String introduce = sc.nextLine();
        try{
          prepare = connect.prepareStatement("UPDATE Information SET EMAIL = ?, INTRODUCE = ? WHERE CUSTOMER = ?");
          prepare.setString(1, email);
          prepare.setString(2, introduce);
          prepare.setString(3, customer);
          prepare.executeUpdate();
            System.out.println("Your information is updated.");
        }catch(SQLException e){
            System.out.println("");
        }        
        
    }
}
