package src.classes.base;

import java.util.HashMap;

public class Repo {

    public HashMap<Integer, Table> list;
    private Class<? extends Table> type;

    private Repo()
    {
        list = new HashMap<Integer, Table>();
    }

    public Repo(Class<? extends Table> type)
    {
        list = new HashMap<Integer, Table>();
        this.type = type;
    }

    public Class<? extends Table> getType()
    {
        return type;
    }

    public void addInstance(Table instance)
    {
        list.put(instance.hashCode(), instance);
    }

    public void removeInstance(Table instance) {
        list.remove(instance);
    }

    @Override
    public int hashCode()
    {
        return 31 * type.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Repo repo)
            return getType().equals(repo.getType());

        return false;
    }



}
