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

    public static int readIntAllowEnter(String question, int pre) {
        while (true) {
            try {
                System.out.println(question);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    return pre;
                }
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!");
                sc.nextLine();
            }

        }
    }

    public static boolean readBooleanAllowEnter(String question, boolean pre) {
        while (true) {
            System.out.println(question);
            String input = sc.nextLine();
            if (input.isEmpty()) {
                return pre;
            }
            if (input.equals("1")) {
                return true;
            } else if (input.equals("0")) {
                return false;
            } else {
                System.out.println("Invalid input!!");
            }
        }

    }

    public static double readDoubleAllowEnter(String question, double pre) {
        while (true) {
            try {
                System.out.println(question);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    return pre;
                }

                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!");
                sc.nextLine();
            }

        }
    }

    public static float readFloatAllowEnter(String question, float pre) {
        while (true) {
            try {
                System.out.println(question);
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    return pre;
                }

                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!");
                sc.nextLine();
            }

        }
    }

    public static String readStringAllowEnter(String question, String pre) {
        System.out.println(question);
        String input = sc.nextLine();
        if (input.isEmpty()) {
            return pre;
        }
        return input;

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
                float input = sc.nextFloat();
                if (input > 0 && input <= 5) {
                    return input;
                } else {
                    System.out.println("Invalid input!!. Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong datatype. Try again !!");
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
