package src;

import src.classes.base.*;

import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception
    {
        Database dbo = new Database();

        Table1 table = new Table1(1, 2, "Daniel");
        Table1 table2 = new Table1(2, 1, "Daniel");


        //System.out.println(table.hashCode());
        //System.out.println(table2.hashCode());
        //System.out.println(table.equals(table2));

        HashMap<Integer, Table> hash = new HashMap<Integer, Table>();

        hash.put(table.hashCode(), table);
        hash.put(table2.hashCode(), table2);

        System.out.println(table.equals(table2));


        System.out.println(table);
        System.out.println(table2);

        System.out.println(hash.get(table2.hashCode()));
        System.out.println(hash.values().size());



        //dbo.addInstance(table);
        //dbo.addInstance(table2);
    }

}
