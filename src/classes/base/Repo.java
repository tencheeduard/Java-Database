package src.classes.base;

import java.util.HashMap;
import java.util.List;

public class Repo<T extends Table> {

    public HashMap<Integer, T> list;
    private Class<? extends Table> type;


    public static <S extends Table> Repo<S> newRepo(Class<S> instanceClass)
    {
        Repo<S> repo = new Repo<S>();
        repo.type = instanceClass;

        return repo;
    }

    private Repo()
    {
        list = new HashMap<Integer, T>();
    }

    public Class<? extends Table> getType()
    {
        return type;
    }

    public void addInstance(T instance)
    {
        list.put(instance.hashCode(), instance);
    }

    public void removeInstance(T instance) {
        list.remove(instance);
    }


}
