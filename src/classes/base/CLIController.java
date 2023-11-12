package src.classes.base;

import java.util.Scanner;

public class CLIController {

    Database base;
    public void start(){
        System.out.println("Type of db:");
        String[] options={"List"};
        for (int i=0; i< options.length; i++){
            System.out.println(options[i]);
        }
    }

}
