package src.classes.controllers;

import src.classes.base.ArrayHelper;
import src.classes.base.Database;
import src.classes.base.Table;

import java.lang.reflect.Field;

public class QueryController {
    public static void displayColumn(Database dbo, String tableName, String column, int length, String columnName) throws Exception {
        Table[] tables = dbo.getTables(tableName);

        Field property = tables[0].getClass().getDeclaredConstructor().newInstance().getField(column);

        word(property.getName().toUpperCase().charAt(0) + property.getName().substring(1)+"\n");
        for(int i = 0; i < length; i++)
            word("-");

        word("\n");
        for(Table tb: tables)
        {
            word(property.get(tb) + "\n");
        }
    }

    public static String[] getColumn(Database dbo, String tableName, String column) throws  Exception
    {
        Table[] tables = dbo.getTables(tableName);

        Field property = tables[0].getClass().getDeclaredConstructor().newInstance().getField(column);

        String[] result = new String[0];

        for(Table tb: tables)
        {
            result = ArrayHelper.addElement(result, property.get(tb).toString());
        }

        return result;
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

    public static void displayQuery(Database dbo, String tableName, String[] columns, Integer[] columnLengths) throws Exception
    {
        int length = 0;
        Table[] tables = dbo.getTables(tableName);

        Field[] properties = new Field[0];

        for(int i = 0; i < columns.length; i++)
            properties = ArrayHelper.addElement(properties, tables[0].getField(columns[i]));

        String[][] rows = new String[tables.length][0];

        for(int i = 0; i < rows.length; i++)
        {
            for(int j = 0; j < properties.length; j++)
            {
                rows[i] = ArrayHelper.addElement(rows[i], properties[j].get(tables[i]).toString());
            }
        }

        for(Field o: properties)
        {
            Integer len = o.getName().length();
            System.out.print(o.getName() + " ");
        }
        System.out.print('\n');

        for(int i = 0; i < rows.length; i++)
        {
            for(int j =0 ; j < rows[i].length; j++)
            {
                System.out.print(rows[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}