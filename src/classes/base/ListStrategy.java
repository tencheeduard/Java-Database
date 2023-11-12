package src.classes.base;

import src.classes.repos.ListRepo;

import java.util.ArrayList;
import java.util.List;

public class ListStrategy implements DbStrategy{

    List<ListRepo<?>> list;

    @Override
    public boolean add(Table table) throws Exception{
        Class<?> clazz= table.getClass();

        ListRepo<?> repo = ListRepo.newRepo(clazz);

        int repoIndex;

        if(!contains(repo))
            list.add(repo);

        repoIndex = list.indexOf(repo);


        return list.get(repoIndex).add(table);

    }

    @Override
    public boolean remove(Table table) throws Exception{
        Class<?> clazz= table.getClass();

        ListRepo<?> repo = ListRepo.newRepo(clazz);

        int repoIndex;

        if(!contains(repo))
            list.add(repo);

        repoIndex = list.indexOf(repo);

        return list.get(repoIndex).remove(table);
    }

    @Override
    public Table get(String name) {
        return null;
    }

    @Override
    public boolean contains(Repo<?> repo){
        return list.contains(repo);
    }
    public ListStrategy(){
        list=new ArrayList<>();
    }

}
