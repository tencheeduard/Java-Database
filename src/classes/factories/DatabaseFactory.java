package src.classes.factories;

import src.classes.base.Database;
import src.classes.strategies.ListStrategy;

public class DatabaseFactory {

    public static Database newDb(String string){
        switch (string){
            case "List":
                return new Database(new ListStrategy());
            default:
                return null;
        }

    }
}
