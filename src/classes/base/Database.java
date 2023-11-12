package src.classes.base;

import src.classes.repos.ListRepo;

import java.util.HashMap;

public class Database {

    DbStrategy strategy;

    public Database(DbStrategy strategy)
    {
        this.strategy=strategy;
    }

    public boolean add(Table table) throws Exception{
        return strategy.add(table);
    }

    public boolean contains(Repo<?> repo){
        return strategy.contains(repo);
    }

    public Table[] getTables(){
        return strategy.getAll();
    }

    public Table[] getTables(String tableName){
        return strategy.get(tableName);
    }
}
