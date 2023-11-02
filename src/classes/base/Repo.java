package src.classes.base;

import java.lang.reflect.Array;
import java.util.List;

public class Repo<T extends Table> {

    List<T> list;

    public Repo()
    {
        list = List.of();
    }

    public void addInstance(T instance)
    {
        list.add(instance);
    }

    public void removeInstance(T instance) {
        list.remove(instance);
    }

    public static <S extends Table> Repo<S> newRepo(Class<S> instanceClass)
    {
        return new Repo<S>();
    }


}
