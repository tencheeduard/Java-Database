package src.classes.base;

import java.util.List;

public class Database {
    List<Repo<?>> list;

    public Database()
    {
        list = List.of();
    }

    public void addInstance(Table instance) throws Exception {
        boolean foundTable = false;
        for(Repo repo: list)
        {
            for(Object table: repo.list)
            {
                if(table.getClass() == instance.getClass())
                    foundTable = true;
            }
        }

        System.out.println(foundTable);

        if(foundTable == false)
        {
            Repo<?> repo = Repo.newRepo(instance.getClass());
            list.add(repo);
        }

        System.out.println(list.get(0));
    }
}
