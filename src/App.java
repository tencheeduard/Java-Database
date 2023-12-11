package src;

import src.classes.base.DatabaseProxy;
import src.classes.controllers.CLIController;
import src.classes.factories.ProxyFactory;
import src.classes.strategies.MySQLStrategy;
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

        MySQLStrategy strategy = new MySQLStrategy("localhost", "3306", "petshop", "root", "edii");

        System.out.println(strategy.getQueryResults("SELECT idanimal as 'lol' FROM Animal"));

        cli.start();

    }
}