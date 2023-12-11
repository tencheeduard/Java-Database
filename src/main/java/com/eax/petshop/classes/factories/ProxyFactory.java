package com.eax.petshop.classes.factories;


import com.eax.petshop.classes.base.Database;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.strategies.ListStrategy;
import com.eax.petshop.classes.strategies.MySQLStrategy;

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
