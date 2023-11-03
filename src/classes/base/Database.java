package src.classes.base;

import java.util.HashMap;

public class Database {
    HashMap<Integer, Repo<?>> map;

    public Database()
    {
        map = new HashMap<Integer, Repo<?>>();
    }

    public void addInstance(Table instance) throws Exception
    {
        boolean foundTable = map.containsKey(instance.hashCode());

        if(!foundTable)
        {
            Repo<?> repo = Repo.newRepo(instance.getClass());

            //map.put();
        }


    }
}
