package src.classes.strategies;

import src.classes.base.DatabaseStrategy;
import src.classes.base.Repo;
import src.classes.base.Table;

public class MySQLStrategy implements DatabaseStrategy {

    public MySQLStrategy()
    {

    }

    @Override
    public boolean add(Table table) throws Exception {
        return false;
    }

    @Override
    public boolean remove(Table table) throws Exception {
        return false;
    }

    @Override
    public Table[] get(String name) {
        return new Table[0];
    }

    @Override
    public boolean contains(Repo<?> repo) {
        return false;
    }

    @Override
    public Table[] getAll() {
        return new Table[0];
    }
}
