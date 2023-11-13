package src.classes.factories;

import src.classes.base.Database;
import src.classes.strategies.ListStrategy;

public class DatabaseFactory {

    public static Database newDb(String name, String type){
        switch (type){
            case "List":
                return new Database(name, new ListStrategy());
            default:
                return null;
        }
    }
}
