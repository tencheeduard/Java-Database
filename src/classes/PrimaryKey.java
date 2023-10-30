package src.classes;

import java.lang.reflect.Type;

public class PrimaryKey<T> {
    private T value;

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

}
