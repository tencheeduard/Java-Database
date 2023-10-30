package src;

import src.classes.Studen;
import src.classes.Table;

public class App {

    public static void main(String[] args) throws Exception
    {
        Studen table = new Studen();

        System.out.println(table.getProperty(1).getType());
    }

}
