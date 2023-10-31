package src.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

public class Table {


    // all tables must have a primary key
    public Table() throws Exception {
        if (!checkPrimaryKey())
            throw new Exception("Table has no Primary Key.");
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

    public List<Integer> getPrimaryKeyIndexes()
    {
        List<Integer> result = new java.util.ArrayList<>(List.of()); //??
        Field[] fields = getProperties();

        for(Integer i = 0; i < fields.length; i++)
            if(fields[i].getType() == PrimaryKey.class)
                result.add(i);

        if(!result.isEmpty())
            return result;
        return null;
    }

    public List<Type> getPrimaryKeyTypes()
    {
        List<Integer> primaryKeys = getPrimaryKeyIndexes();
        List<Type> result = List.of();

        for(int i: primaryKeys)
            result.add(getProperty(i).getType());

        if(!result.isEmpty())
            return result;
        return null;
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


    public boolean compare(Table other)
    {
        boolean result = true;
        if(getClass().equals(other.getClass()))
        {
            List<Integer> primaryKeys = getPrimaryKeyIndexes();
            List<Integer> otherPrimaryKeys = other.getPrimaryKeyIndexes();

            if (primaryKeys.size() == otherPrimaryKeys.size())
            {
                for (int i = 0; i < primaryKeys.size(); i++)
                {
                    
                }
                return result;
            }
        }
        return false;
    }


}
