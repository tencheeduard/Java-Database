package src.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Table {

    public Field[] primaryKeys;

    public Table() throws Exception {
        primaryKeys = new Field[0];
        if(!checkPrimaryKey())
            throw new Exception("Table does not have a primary Key.");
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

        for (Field field : fields)
        {
            if (field.getName().equals(name))
                return field;
        }

        return null;
    }

    public boolean compare(Table other)
    {
        if(this.getClass().equals(other.getClass()))
        {
            if (primaryKeys.length == other.primaryKeys.length)
            {
                for (int i = 0; i < primaryKeys.length; i++)
                {
                    System.out.println(primaryKeys[i].getName());
                }
            }
        }
        return true;
    }

    private boolean checkPrimaryKey() throws Exception
    {
        Field[] fields = getProperties();

        for(Field field: fields)
            if(field.isAnnotationPresent(PrimaryKey.class))
            {
                if(!field.getType().isPrimitive())
                {
                    primaryKeys = Arrays.copyOf(primaryKeys, primaryKeys.length + 1);
                    primaryKeys[primaryKeys.length - 1] = field;
                }
                else throw new Exception("Primary Key may not be Primitive.");
            }

        return primaryKeys.length > 0;
    }

}
