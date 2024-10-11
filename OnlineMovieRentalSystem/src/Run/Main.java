/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import JDBC.ConnectJDBC;
import Movies.Movie;

/**
 *
 * @author ngoct
 */
public class Main {
    public static void main(String[] args) {
        ConnectJDBC.init();
        Movies.Movie m = new Movie(1, 2, "vvds", "ZDFvrevrse", 4, true, 23, 2);
        System.out.println(m);
    }
}
