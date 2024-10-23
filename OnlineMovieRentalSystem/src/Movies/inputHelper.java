/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movies;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class inputHelper {

    public static Scanner sc = new Scanner(System.in);

    public static int readInt(String question) {
        while (true) {
            try {
                System.out.print(question);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input. Try again !!");
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }
    
    public static int readIntAllowEnter(String question){
        while (true) {            
            try {
                System.out.println(question);
                String input = sc.nextLine();
                
            } catch (Exception e) {
            }
        }
    }

    public static String readString(String question) {
        while (true) {
            try {
                System.out.print(question);
                String s = sc.nextLine();
                if (s != null) {
                    System.out.println("Input again !!");
                }
                return s;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static float readFloat(String question) {
        while (true) {
            try {
                System.out.print(question);
                return sc.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input. Try again !!");
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }

    public static double readDouble(String question) {
        while (true) {
            try {
                System.out.print(question);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input. Try again !!");
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }

    public static boolean readBoolean(String question) {
        while (true) {
            try {
                System.out.print(question);
                int index = sc.nextInt();
                if (index == 1) {
                    return true;
                } else if (index == 0) {
                    return false;
                } else {
                    System.out.println("Only enter 0 or 1");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again!!");
                sc.nextLine();
            }
        }
    }

}
