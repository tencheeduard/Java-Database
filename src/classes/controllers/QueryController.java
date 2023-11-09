package src.classes.controllers;

import src.classes.base.Database;
import src.classes.base.Table;

import java.lang.reflect.Field;
import java.util.Arrays;

public class QueryController {
    public static void displayColumn(Database dbo, String tableName, String column, int length, String columnName) throws Exception {
        Table[] tables = dbo.getTables(tableName);

        Field property = tables[0].getClass().getDeclaredConstructor().newInstance().getProperty(column);

        word(property.getName().toUpperCase().charAt(0) + property.getName().substring(1)+"\n");
        for(int i = 0; i < length; i++)
            word("-");

        word("\n");
        for(Table tb: tables)
        {
            word(property.get(tb) + "\n");
        }
    }

    public static void displayColumn(Database dbo, String tableName, String column) throws Exception {
        displayColumn(dbo, tableName, column, 10, column);
    }

    public static void displayColumn(Database dbo, String tableName, String column, String columnName) throws Exception {
        displayColumn(dbo, tableName, column, 10, columnName);
    }

    public static void word(String word)
    {
        System.out.print(word);
    }

    public static void displayQuery(Database dbo, String[] tableNames, String[] columns)
    {
        int length = 0;
        Table[][] tables = new Table[tableNames.length][0];

        for(int i = 0; i < tableNames.length; i++)
        {
            Table[] currentArray = dbo.getTables(tableNames[i]);
            for(int j = 0; j < )
            tables[i] = Arrays.copyOf()
        }
    }

}








//ursulet