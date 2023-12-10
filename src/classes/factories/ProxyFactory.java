package src.classes.factories;

import src.classes.base.Database;
import src.classes.base.DatabaseProxy;
import src.classes.strategies.ListStrategy;
import src.classes.strategies.MySQLStrategy;

import java.sql.SQLException;

public class ProxyFactory {

    public static String[] options()
    {
        String[] list = {"List", "MySQL"};
        return list;
    }

    public static DatabaseProxy create(String name, String type, String... args) {
        switch (type){
            case "List":
                Database dblist = new Database(name, new ListStrategy());
                return new DatabaseProxy(dblist);
            case "MySQL":
                Database dbsql = new Database(name, new MySQLStrategy(args[0], args[1], args[2], args[3], args[4]));
                return new DatabaseProxy(dbsql);
            default:
                return null;
        }
    }
}
