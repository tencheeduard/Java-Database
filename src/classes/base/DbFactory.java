package src.classes.base;

public class DbFactory {

    public static Database newDb(String string){
        switch (string){
            case "List":
                return new Database(new ListStrategy());
            default:
                return null;
        }
        
    }
}
