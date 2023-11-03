package src.classes.repos;

import src.classes.base.Repo;
import src.classes.base.Table;

import java.util.ArrayList;
import java.util.List;

public class ListRepo<T extends Table> extends Repo<T> {

    protected List<T> list;

    protected ListRepo() {}

    public ListRepo(Class<T> type)
    {
        list = new ArrayList<T>();
        this.type = type;
    }

    @Override
    public boolean addToRepo(T instance) {
        return list.add(instance);
    }

    @Override
    public boolean remove(T instance) {
        return list.remove(instance);
    }

    @Override
    public T getValue(int index) {
        return list.get(index);
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
