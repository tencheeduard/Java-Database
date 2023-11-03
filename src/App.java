package src;

import src.classes.base.*;
import src.classes.repos.ListRepo;

import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception
    {
        Database dbo = new Database();

        Table1 table = new Table1(1, 2, "Daniel");
        Table1 table2 = new Table1(2, 1, "Nathaniel");


        //System.out.println(table.hashCode());
        //System.out.println(table2.hashCode());
        //System.out.println(table.equals(table2));

        ListRepo<Table1> repo = new ListRepo<>(Table1.class);


        repo.add(table);
        repo.add(table2);
        repo.add(table2);
        repo.add(table2);
        repo.add(table2);
        repo.add(table2);

        for(int i = 0; i < repo.getSize(); i++)
            System.out.println(repo.getValue(i));



        //dbo.addInstance(table);
        //dbo.addInstance(table2);
    }

}
