package src;

import src.classes.Studen;

public class App {

    public static void main(String[] args) throws Exception
    {
        Studen table = new Studen("abc", 1);
        Studen table2 = new Studen("abc", 2);


        table.setPrimaryKey1(50.0);
        System.out.println(table.id);
    }

}
