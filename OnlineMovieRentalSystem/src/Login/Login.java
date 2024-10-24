package Login;
import Movies.MoviesManagement;
import CustomerRental.CustomerMainPage;
import JDBC.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
         InputMain input = new InputMain();
        Connection connect = ConnectJDBC.getConnection();
        PreparedStatement Check = null;
    public void toLogin() throws SQLException{
        
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
        LoginRight(user);
//        String username = input.InputString("Please enter username: ");
//        String password = input.InputString("Please enter password: ");
//        
//         if(user.equals("Admin")){
//                MoviesManagement manage = new MoviesManagement();
//                LoginRight(username, password, "AdminAccount.txt", manage);
//                
//        }else if(user.equals("Customer")){
//            CustomerMainPage cusRent = new CustomerMainPage();
//            LoginRight(username, password, "CustomerAccount.txt", cusRent);
//        }
    }
    
//    public void LoginRight(String username, String password, String filename, Object right){
//        try(BufferedReader read = new BufferedReader(new FileReader(filename))){
//           String admin;
//           boolean check = false;
//           while((admin = read.readLine()) != null){
//               String[] split = admin.split(", ");
//               if(split[0].equals(username) && split[1].equals(password)){
//                   if(right instanceof MoviesManagement){
//                     //  manage.manageMovie(admin);
//                       check = true;
//                   }else if(right instanceof CustomerMainPage){
//                       CustomerMainPage customer = new CustomerMainPage();
//                       customer.Page(username);
//                       check = true;
//                   };
//               }
//           }
//           if(check == false){
//                   System.out.println("Your username or password might not correct.");
//           }
//        }catch(IOException e){
//            System.out.println("Login failed!");
//        }
//    }
    
    public void LoginRight(String user) throws SQLException{
        String query = "SELECT * FROM LOGIN_USER WHERE POSITION = ? AND USERNAME = ? AND PASS = ?";
        try{
            Check = connect.prepareStatement(query);
            while(true){
                String username = input.InputString("Enter your username(\"Quit\" to exit): ");
                if (username.equalsIgnoreCase("quit")) {
                System.exit(0);
                }
                String password = input.InputString("Enter your password(\"Quit\" to exit): ");
                if (password.equalsIgnoreCase("quit")) {
                System.exit(0);
                }
                Check.setString(1, user);
                Check.setString(2, username);
                Check.setString(3, password);
                ResultSet result = Check.executeQuery();
                if(result.next()){
                        if(user.equals("Admin")){
                            System.out.println("Login successful");
                            //Waiting for Tuyen
                            break;
                        }else if(user.equals("Customer")){
                         System.out.println("Login successful");
                         CustomerMainPage customer = new CustomerMainPage();
                         customer.Page(username);
                         break;
                    }
                }else{
                        System.out.println("Your username or password might be wrong.");
                    }
            }
        }catch(SQLException e){
            System.out.println("Connection error!");
        }finally{
            if(connect != null){
                connect.close();
            }
        }
    }
}