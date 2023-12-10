package src;

import src.classes.base.ArrayHelper;
import src.classes.controllers.CLIController;
import src.classes.tables.Animal;
import src.tests.DatabaseFactoryTest;
import src.tests.ObserverTest;

import java.lang.reflect.Field;

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

        cli.invoke("cdb db mysql localhost 3306 petshop root edii");

        cli.start();

    }
}