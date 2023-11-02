package src;

import src.classes.OtherTable;
import src.classes.Studen;

public class App {

    public static void main(String[] args) throws Exception
    {
        Studen table = new Studen("abc", 1);
        OtherTable table2 = new OtherTable("abc", 1);


        System.out.println(table.compare(table2));
    }

}
