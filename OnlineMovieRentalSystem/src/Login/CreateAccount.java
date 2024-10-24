package Login;

import JDBC.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccount {
    InputMain input = new InputMain();
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement Check = null;
            
    public void Account() throws SQLException{
        String user = "";
        boolean choose = true;
        while(choose){
        user = input.InputString("\nYour are: \n1.Admin\n2.Customer\nPlease enter 1 or 2 to choose: ");
        switch(user){
            case "1":
                user = "Admin";
                choose = false;
                break;
                
            case "2":
                user = "Customer";
                choose = false;
                break;
                
        }
     }
        String username = username();
        String password = password();
        al:
        while(true){
         boolean confirm = Confirm(password);
         if(confirm == true){
             break;
         }else{
             String ans = input.InputString("Wrong. Want to try again?[Y/N]: ").toLowerCase();
             if(ans.equals("n")){
                 System.exit(0);
             }
         }
       }
       
        insertData(user, username, password);
    }
    
 public String username() throws SQLException{
    String query = "SELECT * FROM LOGIN_USER WHERE USERNAME = ?";
    String username = "";
    
    try {
        
        Check = connect.prepareStatement(query);
        
        while (true) {
            username = input.InputString("Please enter your username (Enter \"Quit\" if you want to exit): ");
            if (username.equalsIgnoreCase("quit")) {
                System.exit(0);
            } else {
                Check.setString(1, username);
                ResultSet result = Check.executeQuery();
                
                if (result.next()) {
                    System.out.println("Your username is already in use.");
                } else {
                    return username;
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Can't connect");
    } finally {
        if (Check != null) {
            Check.close();
        }
    }
    return username;
}
    
    public String password(){
        boolean loop = true;
        String pass = "";
        while(loop){
           pass = input.InputString("Please enter your password(Password must have at least 8 characters): ");
           if(pass.length() >= 8){
               loop = false;
           }
        }
        return pass;
    }
    
    public boolean Confirm(String password){
        String confirm = input.InputString("Please confirm your password: ");
        if(confirm.equals(password)){
            return true;
        }
        return false;
    }
    
//    public void SaveCreate(String username, String password, String filename){
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
//            writer.write(username + ", " + password);
//            writer.newLine();
//            writer.close();
//            System.out.println("Create successful!");
//            String answer = input.InputString("Do you want to continue to login?[Y/N]: ").toLowerCase();
//            if(answer.equals("y")){
//                Login login = new Login();
//                login.toLogin();
//            }else{
//                System.exit(0);
//            }
//        }catch(IOException e){
//            System.out.println("Create failed!");
//        }
//    }
    
    public void insertData(String user, String username, String password) throws SQLException{
        String query = "INSERT INTO LOGIN_USER (POSITION, USERNAME, PASS) VALUES (?, ?, ?)";
        try{
        Check = connect.prepareStatement(query);
        Check.setString(1, user);
        Check.setString(2, username);
        Check.setString(3, password);
        Check.executeUpdate();
        
        System.out.println("Create successful!");
        String answer = input.InputString("Do you want to continue to login?[Y/N]: ").toLowerCase();
            if(answer.equals("y")){
                Login login = new Login();
                login.toLogin();
            }else{
                System.exit(0);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(connect != null){
                connect.close();
            }
        }
    }
}