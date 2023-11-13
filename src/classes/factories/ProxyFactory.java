package src.classes.factories;

import src.classes.base.Database;
import src.classes.base.DatabaseProxy;
import src.classes.strategies.ListStrategy;

public class ProxyFactory {

    public static DatabaseProxy create(String name, String type){
        switch (type){
            case "List":
                Database db = new Database(name, new ListStrategy());
                return new DatabaseProxy(db);
            default:
                return null;
        }
    }
}
