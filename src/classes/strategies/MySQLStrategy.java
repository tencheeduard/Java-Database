package src.classes.strategies;

import src.classes.base.DatabaseStrategy;
import src.classes.base.Repo;
import src.classes.base.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
