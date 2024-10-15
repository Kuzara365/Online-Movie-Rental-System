/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author votha
 */

public class Login {
    private String username;
    private String password;
    
    public Login(String username, String password){
        this.username=username;
        this.password=password;
    }
    
    public void printLoginDetails(){
        System.out.println("Username: "+username);
        System.out.println("Password: "+password);
    }
    
    public void comparedWithDataBase(){
        try{
            Connection conn=DriverManager.getConnection("");
            PreparedStatement ps=conn.prepareStatement("");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeQuery();
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void logout(){
        System.out.println("You have been logged out");
    }
}