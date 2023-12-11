package com.eax.petshop.classes.repositories;


import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.base.Repo;
import com.eax.petshop.classes.base.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListRepo<T> extends Repo<T> {

    protected List<T> list;

    protected ListRepo()
    {
        this.list = new ArrayList<T>();
    }

    public ListRepo(Class<T> type)
    {
        list = new ArrayList<T>();
        this.type = type;
    }

    public static <S> ListRepo<S> newRepo(Class<S> clazz) throws Exception {
        ListRepo<S> result;
        if(clazz.getDeclaredConstructor().newInstance() instanceof Table)
            result = new ListRepo<S>();
        else throw new Exception("Repo must contain a Table");

        result.type = clazz;
        return result;
    }

    @Override
    public boolean addToRepo(T instance) {
        return list.add(instance);
    }

    @Override
    public boolean removeFromRepo(T instance) {
        return list.remove(instance);
    }

    @Override
    public T getValue(int index) {
        return list.get(index);
    }

    @Override
    public Integer getMaxIncrement(Field field) throws Exception
    {
        Integer max = -1;
        if(field.isAnnotationPresent(AutoIncrement.class) && field.getType() == Integer.class)
        {
            for (T obj : list)
                if (obj instanceof Table table)
                {
                    Object value = field.get(table);
                    if(value instanceof Integer i && i > max)
                        max = i;
                }
        }
        else throw new Exception("Field does not auto-increment");

        return max;
    }

    @Override
    public int getSize()
    {
        return list.size();
    }

    @Override
    public boolean contains(Object obj) {
        if(obj.getClass() == type)
            return list.contains(obj);
        return false;
    }
}
