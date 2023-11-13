package src.classes.base;

import src.classes.annotations.AutoIncrement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Repo<T> {
    protected Class<T> type;

    public boolean add(Object obj) throws Exception
    {
        T instance = castToGeneric(obj);
        if(instance instanceof Table table)
        {
            for(Field primaryKey: table.primaryKeys)
            {
                if(primaryKey.isAnnotationPresent(AutoIncrement.class) && primaryKey.getType() == Integer.class)
                {
                    primaryKey.set(table, getMaxIncrement(primaryKey)+1);
                }
            }
        }
        if(!contains(instance))
            return addToRepo(instance);
        return false;
    }

    public abstract boolean addToRepo(T instance);

    public boolean remove(Object obj)
    {
        T instance = castToGeneric(obj);
        if(instance != null && !contains(instance))
            return removeFromRepo(instance);
        return false;
    }

    public abstract boolean removeFromRepo(T instance);

    public abstract T getValue(int index);

    public abstract Integer getMaxIncrement(Field field) throws Exception;

    public abstract int getSize();

    public abstract boolean contains(Object obj);

    public Class<?> getType()
    {
        return type;
    }

    public T castToGeneric(Object obj)
    {
        if(obj.getClass() == type)
            return type.cast(obj);
        return null;
    }

    protected Repo() {
    }

    public Repo(Class<T> type) {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        return 31 * type.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Repo<?> repo)
            return getType().equals(repo.getType());
        return false;
    }

}
