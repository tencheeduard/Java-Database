package src.classes;

import java.lang.reflect.Field;

public class Table {


    // all tables must have a primary key
    public Table() throws Exception
    {
        if(!checkPrimaryKey())
            throw new Exception("Table has no Primary Key.");
    }
    private boolean checkPrimaryKey()
    {
        boolean foundPrimaryKey = false;

        Field[] fields = getProperties();

        for(Field field: fields)
            if(field.getType() == PrimaryKey.class)
                return true;

        return false;
    }

    public Field[] getProperties()
    {
        return getClass().getDeclaredFields();
    }
    public Field getProperty(Integer index)
    {
        Field[] fields = getProperties();

        if(index < fields.length && index >= 0)
            return fields[index];
        return null;
    }
    public Field getProperty(String name) {

        Field[] fields = getProperties();

        for (Field field : fields) {
            if (field.getName().equals(name))
                return field;
        }

        return null;
    }
}
