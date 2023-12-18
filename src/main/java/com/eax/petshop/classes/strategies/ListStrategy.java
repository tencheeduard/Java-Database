package com.eax.petshop.classes.strategies;


import com.eax.petshop.classes.base.DatabaseStrategy;
import com.eax.petshop.classes.base.Repo;
import com.eax.petshop.classes.base.Table;
import com.eax.petshop.classes.repositories.ListRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStrategy implements DatabaseStrategy {

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

        for(repoIndex = 0; repoIndex < list.size(); repoIndex++)
            if(list.get(repoIndex).equals(repo))
                break;

        return list.get(repoIndex).remove(table);
    }

    @Override
    public Table[] get(String name) {
        Table[] result = new Table[0];

        int index = 0;

        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getSize() > 0 && list.get(i).getValue(0).getClass().getSimpleName().equalsIgnoreCase(name))
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


    public boolean contains(Repo<?> repo){
        return list.contains(repo);
    }

    @Override
    public Table[] getAll(){
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
    public ListStrategy(){
        list=new ArrayList<>();
    }

}
