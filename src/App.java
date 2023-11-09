package src;

import src.classes.base.*;
import src.classes.controllers.QueryController;
import src.classes.databases.ListDatabase;
import src.classes.repos.ListRepo;

import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception
    {
        ListDatabase dbo = new ListDatabase();

        Table1 table = new Table1(1, 2, "Daniel");
        Table1 table6 = new Table1(1, 2, "Nathaniel");
        Table1 table7 = new Table1(1, 2, "Braniel");
        Table2 table2 = new Table2();
        Table2 table3 = new Table2();
        Table2 table4 = new Table2();
        Table2 table5 = new Table2();


        dbo.add(table);
        dbo.add(table2);
        dbo.add(table3);
        dbo.add(table4);
        dbo.add(table5);
        dbo.add(table6);
        dbo.add(table7);


        Table[] tables = dbo.getTables();
        QueryController.displayColumn(dbo, "Table1", "name", "Person Name");

        //dbo.addInstance(table);
        //dbo.addInstance(table2);
    }

}
