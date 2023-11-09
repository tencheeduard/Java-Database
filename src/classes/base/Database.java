package src.classes.base;

import src.classes.repos.ListRepo;

import java.util.HashMap;

public abstract class Database {

    public Database()
    {

    }

    public abstract boolean add(Object obj) throws Exception;

    public abstract boolean contains(Repo<?> repo);

    public abstract Table[] getTables();

    public abstract Table[] getTables(String tableName);
}
