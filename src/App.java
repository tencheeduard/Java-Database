package src;

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

        Animal table = new Animal("Pomel", "2004-01-01");

        String query = "INSERT INTO " + table.getClass().getSimpleName().toLowerCase() + " (";

        Field[] fields = table.getFields();

        for(int i = 0; i < fields.length; i++)
        {
            query+=fields[i].getName().toLowerCase() + ' ';
            if(i>fields.length-1)
                query+=", ";
        }

        query+= ")\n\tVALUES (";

        for(int i = 0; i < fields.length; i++)
        {
            query+=fields[i].get(table);
            if(i>fields.length-1)
                query+=", ";
        }

        query+= ")";

        System.out.println(query);

        CLIController cli = new CLIController();

        cli.invoke("cdb db list");
        cli.invoke("addtable db Animal");
        cli.invoke("addtable db Animal");

        cli.start();

    }
}