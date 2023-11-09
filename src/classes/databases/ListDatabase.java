package src.classes.databases;

import src.classes.base.Database;
import src.classes.base.Repo;
import src.classes.base.Table;
import src.classes.repos.ListRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDatabase extends Database {

    public List<ListRepo<?>> list;

    public ListDatabase()
    {
        list = new ArrayList<>();
    }


    @Override
    public boolean add(Object obj) throws Exception{

        Class<?> clazz;
        if(obj instanceof Table table)
            clazz = obj.getClass();
        else return false;
        ListRepo<?> repo = ListRepo.newRepo(clazz);

        int repoIndex;

        if(!contains(repo))
            list.add(repo);

        repoIndex = list.indexOf(repo);


        return list.get(repoIndex).add(obj);
    }

    public boolean contains(Repo<?> repo)
    {
        return list.contains(repo);
    }

    public Repo<?> getValue(int index)
    {
        return list.get(index);
    }

    @Override
    public Table[] getTables()
    {
        Table[] result = new Table[0];

        int index = 0;

        for(int i = 0; i < list.size(); i++)
        {
            for(int j = 0; j < list.get(i).getSize(); j++)
            {
                result = Arrays.copyOf(result, result.length+1);
                result[index++] = (Table) list.get(i).getValue(j);
            }
        }

        return result;
    }

    @Override
    public Table[] getTables(String name)
    {
        Table[] result = new Table[0];

        int index = 0;

        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getValue(0).getClass().getSimpleName().equalsIgnoreCase(name))
            {
                for (int j = 0; j < list.get(i).getSize(); j++) {
                    result = Arrays.copyOf(result, result.length + 1);
                    result[index++] = (Table) list.get(i).getValue(j);
                }

                break;
            }

        }

        return result;
    }

}
