/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerRental;

import JDBC.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HuyDepZai
 */
public class Delete {
    Connection connect = ConnectJDBC.getConnection();
    PreparedStatement prepare = null;
    ResultSet result = null;
    public void delete(String customer){
        String removeAccount = "DELETE FROM LOGIN_USER WHERE USERNAME = ?";
        String removeHistory = "DELETE FROM CUSTOMER_INFO WHERE CUSTOMER = ?";
        String checkHistory = "SELECT * FROM CUSTOMER_INFO WHERE CUSTOMER = ?";
        try{
            prepare = connect.prepareStatement(removeAccount);
            prepare.setString(1, customer);
            prepare.executeUpdate();
            
            prepare = connect.prepareStatement(checkHistory);
            prepare.setString(1, customer);
            result = prepare.executeQuery();
            boolean check = false;
            while(result.next()){
                String find = result.getString("CUSTOMER");
                if(find.isEmpty()){
                    break;
                }else{
                    check = true;
                    break;
                }
            }
            if(check == true){
            prepare = connect.prepareStatement(removeHistory);
            prepare.setString(1, customer);
            prepare.executeUpdate();
                System.out.println("1");
            }
            System.out.println("Delete successfully!");
        }catch(SQLException e){
           System.out.println("Error: " + e.getMessage());
        }
    }
}
