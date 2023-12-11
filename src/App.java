package src;

import src.classes.controllers.CLIController;
import src.tests.DatabaseFactoryTest;
import src.tests.ObserverTest;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception
    {
        // Run Tests
        {
            DatabaseFactoryTest.runTests();
            ObserverTest observerTest = new ObserverTest();
            observerTest.test1();
        }

        CLIController cli = new CLIController();

        File passwordFile = new File("src\\password.txt");
        String password = new Scanner(passwordFile).nextLine();

        cli.invoke("cdb db mysql localhost 3306 petshop root " + password);

        cli.start();

    }
}