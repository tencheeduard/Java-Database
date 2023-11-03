package src;

import src.classes.base.Database;
import src.classes.base.Table1;
import src.classes.base.Table2;

public class App {

    public static void main(String[] args) throws Exception
    {
        Database dbo = new Database();

        Table1 table = new Table1(3, "Daniel");
        Table1 table2 = new Table1(3, "Daniel");


        System.out.println(table.hashCode());
        System.out.println(table2.hashCode());
        System.out.println(table.equals(table2));


        //dbo.addInstance(table);
        //dbo.addInstance(table2);
    }

}
