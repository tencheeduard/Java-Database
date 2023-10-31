package src;

import src.classes.PrimaryKey;
import src.classes.Studen;
import src.classes.Table;

public class App {

    public static void main(String[] args) throws Exception
    {
        Studen table = new Studen("abc", 1);
        Studen table2 = new Studen("abc", 2);

        System.out.println(table.compare(table2));
    }

}
