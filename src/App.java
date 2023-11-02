package src;

import src.classes.base.Database;
import src.classes.base.Table;
import src.classes.base.Table1;

public class App {

    public static void main(String[] args) throws Exception
    {
        Database dbo = new Database();

        Table1 table = new Table1();

        dbo.addInstance(table);
    }

}
