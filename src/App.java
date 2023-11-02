package src;

import src.classes.Studen;

public class App {

    public static void main(String[] args) throws Exception
    {
        Studen table = new Studen("abcd", 1);
        Studen table2 = new Studen("abc", 1);


        System.out.println(table.compare(table2));
    }

}
