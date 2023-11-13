package src;

import src.classes.controllers.CLIController;
import src.tests.DatabaseFactoryTest;
import src.tests.ObserverTest;

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

        cli.invoke("cdb db list");
        cli.invoke("addtable db Animal");
        cli.invoke("addtable db Animal");

        cli.start();

    }
}