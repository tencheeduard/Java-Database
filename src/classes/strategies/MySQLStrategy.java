package src.classes.strategies;

import src.classes.base.ArrayHelper;
import src.classes.base.DatabaseStrategy;
import src.classes.base.Repo;
import src.classes.base.Table;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySQLStrategy implements DatabaseStrategy {

    Connection connection;

    String dbName;

    public MySQLStrategy(String address, String port, String dbName, String username, String password)
    {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/" + dbName, username, password);
            this.dbName = dbName;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Table[] getFromQuery(String query)
    {
        try{

            Table[] returnValue = new Table[0];

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            String tableName = "src.classes.tables." + result.getMetaData().getTableName(1).toLowerCase();

            int columnCount = result.getMetaData().getColumnCount();

            Class<?> clazz = Class.forName(tableName);

            while(result.next())
            {
                Table table = (Table) clazz.getDeclaredConstructor().newInstance();
                for(int i = 1; i <= columnCount; i++) {
                    Object value = result.getObject(i);
                    if(value!=null) {
                        if (value.getClass() == java.sql.Date.class)
                            value = value.toString();

                        table.setProperty(result.getMetaData().getColumnLabel(i), value);
                    }
                }

                returnValue = ArrayHelper.addElement(returnValue, table);
            }

            return returnValue;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean add(Table table) throws Exception {
        try {
            String query = "INSERT INTO " + table.getClass().getSimpleName().toLowerCase() + " (";

            Field[] fields = ArrayHelper.except(table.getFields(), table.getPrimaryKeys());

            for (int i = 0; i < fields.length; i++) {
                query += fields[i].getName().toLowerCase();
                if (i < fields.length - 1)
                    query += ", ";
            }

            query += ")\n\tVALUES (";

            for (int i = 0; i < fields.length; i++) {
                query += fields[i].get(table);
                if (i < fields.length - 1)
                    query += ", ";
            }

            query += ")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Table table) throws Exception {

        return false;

    }

    @Override
    public Table[] get(String name) {
        return getFromQuery("SELECT * FROM " + name);
    }

    @Override
    public Table[] getAll() {
        try {
            List<Table> resultList = new ArrayList<>();

            String query = "show tables from " + dbName;

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            String[] tables = new String[0];

            while(results.next())
                tables = ArrayHelper.addElement(tables, results.getString(1));

            for(String str: tables)
            {
                Table[] queryResult = getFromQuery("SELECT * FROM " + str);
                resultList.addAll(Arrays.asList(queryResult));
            }

            Table[] result = resultList.toArray(new Table[0]);

            return result;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
