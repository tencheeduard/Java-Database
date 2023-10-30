package src.controller;

import src.classes.Table;

import java.io.Serializable;
import java.lang.reflect.Field;

public class DBController {


    public static Field getProperty(Table table, Integer index)
    {
        Field[] properties = table.getClass().getDeclaredFields();

        if(index < properties.length)
            return properties[index];
        return null;
    }
    public static Field getProperty(Table table, String name) {
        Field[] properties = table.getClass().getDeclaredFields();

        for (Field field : properties) {
            if (field.getName().equals(name))
                return field;
        }

        return null;
    }

}
