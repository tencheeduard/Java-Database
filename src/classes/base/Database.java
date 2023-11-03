package src.classes.base;

import src.classes.repos.ListRepo;

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

        Repo<?> repo = new ListRepo<>(instance.getClass());

        if(!foundTable)
        {

            map.put(repo.hashCode(), repo);
        }
        else
        {
            if(!repo.contains(instance) && instance.getClass() == repo.getType())
                ;//repo.add(repo.getType().cast(instance));
        }


    }
}
