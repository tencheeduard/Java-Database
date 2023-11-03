package src.classes.base;

import java.util.HashMap;

public class Database {
    HashMap<Integer, Repo> map;

    public Database()
    {
        map = new HashMap<Integer, Repo>();
    }

    public void addInstance(Table instance) throws Exception
    {
        boolean foundTable = map.containsKey(instance.hashCode());

        Repo repo = new Repo(instance.getClass());

        if(!foundTable)
        {

            map.put(repo.hashCode(), repo);
        }
        else
        {
            if(!repo.list.containsValue(instance))
                repo.list.put(instance.hashCode(), instance);
        }


    }
}
