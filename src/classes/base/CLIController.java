package src.classes.base;

import java.util.Scanner;

public class CLIController {

    Database base;
    public void start(){
        System.out.println("Type of db:");
        System.out.println("1.List");
        System.out.println("2.CvCute<3");
        Scanner scanner = new Scanner(System.in);
        int choice= scanner.nextInt();
        if (choice==1){
            base=new Database(new ListStrategy());
        }
    }

}
