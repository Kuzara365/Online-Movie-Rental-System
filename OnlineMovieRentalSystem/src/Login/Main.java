package Login;

import java.sql.SQLException;
import JDBC.ConnectJDBC;

public class Main {

    public static void main(String[] args) throws SQLException {
        InputMain input = new InputMain();
        ConnectJDBC.init();
        System.out.println("Hello, Welcome to our movie booking's website.");
        boolean loop = true;
        while (loop) {
            String haveAccount = input.InputString("Do you have an account yet?[Y/N]: ").toUpperCase();
            switch (haveAccount) {
                case "Y":
                    loop = false;
                    Login login = new Login();
                    login.toLogin();
                    break;

                case "N":
                    loop = false;
                    CreateAccount acc = new CreateAccount();
                    acc.Account();
                    break;

                default:
                    System.out.println("Please only enter 'Y' or 'N'");
            }
        }
    }
}
