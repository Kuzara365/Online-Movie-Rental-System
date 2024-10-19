package test.movierental;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Inputter {
    
    private Scanner sc = new Scanner(System.in);
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public int inputInteger(String request) {
        System.out.print(request);
        return sc.nextInt();
    }
    
    public double inputDouble(String request) {
        System.out.print(request);
        return sc.nextDouble();
    }
    
    public String inputString(String request) {
        System.out.print(request);
        sc.nextLine();
        return sc.nextLine();
    }
    
    public Date inputDate(String request) {
        System.out.print(request);
        String dateString = sc.nextLine();
        return Date.valueOf(dateString);
    }
    
    public String getCurrentFormattedDate() {
        LocalDateTime rentTime = LocalDateTime.now();
        return rentTime.format(formatTime);
    }

    public void close() {
        sc.close();
    }
}
