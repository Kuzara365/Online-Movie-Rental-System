package CustomerRental;

import JDBC.ConnectJDBC;
import Login.InputMain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HuyDepZai
 */
public class Delete {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    public void delete(String customer){
        String removeAccount = "DELETE FROM LOGIN_USER WHERE USERNAME = ?";
         while(true){
        String haveAccount = input.InputString("Delete your account?[Y/N]: ").toUpperCase();
        switch(haveAccount){
            case "Y":               
           try{
            prepare = connect.prepareStatement(removeAccount);
            prepare.setString(1, customer);
            prepare.executeUpdate();
            System.out.println("Delete successfully!");
            System.exit(0);
           }catch(SQLException e){
           System.out.println("Error: " + e.getMessage());
        }
                break;
                
            case "N":
                return;
                
            default:
                System.out.println("Please only enter 'Y' or 'N'");
        }
       }
    }
}
