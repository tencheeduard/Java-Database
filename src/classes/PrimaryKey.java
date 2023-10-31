package src.classes;

import java.lang.reflect.Type;

public class PrimaryKey<T extends Object> {

    public T value;

    public Type getValueClass()
    {
        return value.getClass();
    }

    public PrimaryKey() throws Exception
    {
        value = null;
    }

    public PrimaryKey(T value)
    {
        this.value = value;
    }


    @Override
    public boolean equals(Object obj)
    {
        System.out.println("A");
        if(obj instanceof PrimaryKey<?> objPrimaryKey &&
            value.getClass().equals(objPrimaryKey.value.getClass()))

                return value.equals(objPrimaryKey.value);

        return false;
    }

}
