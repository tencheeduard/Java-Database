package src;

import src.classes.Studen;
import src.classes.Table;
import src.controller.DBController;

import java.lang.reflect.Field;

public class App {

    public static void main(String[] args)
    {
        Studen studen = new Studen();

        System.out.println(DBController.getProperty(studen, 1).getType());
    }

}
