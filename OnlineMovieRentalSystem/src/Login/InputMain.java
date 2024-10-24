package Login;

import java.util.Scanner;

public class InputMain {
    Scanner sc = new Scanner(System.in);
    public String InputString(String sentence){
        System.out.print(sentence);
        return sc.nextLine();
    }
    
    public int InputInteger(String sentence){
        System.out.println(sentence);
        return sc.nextInt();
    }
    
   public double InputDouble(String sentence){
        System.out.println(sentence);
        return sc.nextDouble();
    }
}
