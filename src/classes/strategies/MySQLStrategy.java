package src.classes.strategies;

import src.classes.base.DatabaseStrategy;
import src.classes.base.Repo;
import src.classes.base.Table;

import java.lang.reflect.Field;
import java.sql.*;

public class MySQLStrategy implements DatabaseStrategy {

    Connection connection;

    public MySQLStrategy(String address, String port, String dbName, String username, String password)
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + address + ":" + port + "/" + dbName, username, password);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public boolean add(Table table) throws Exception {

        String query = "INSERT INTO " + table.getClass().getSimpleName().toLowerCase() + " (";

        Field[] fields = table.getFields();

        for(int i = 0; i < fields.length; i++)
        {
            query+=fields[i].getName().toLowerCase();
            if(i>fields.length-1)
                query+=", ";
        }

        query+= ")\n\tVALUES (";

        for(int i = 0; i < fields.length; i++)
        {
            query+=fields[i].get(table);
            if(i>fields.length-1)
                query+=", ";
        }

        query+= ")";

        System.out.println(query);

        Statement statement = connection.createStatement();
        //ResultSet resultSet = statement.executeQuery();
        return false;
    }

    @Override
    public boolean remove(Table table) throws Exception {
        return false;
    }

    @Override
    public Table[] get(String name) {
        return new Table[0];
    }

    @Override
    public boolean contains(Repo<?> repo) {
        return false;
    }

    @Override
    public Table[] getAll() {
        return new Table[0];
    }
}
