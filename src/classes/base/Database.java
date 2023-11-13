package src.classes.base;

public class Database extends Observable {

    String name;
    DatabaseStrategy strategy;

    public Database(String name)
    {
        this.name = name;
    }

    public Database(String name, DatabaseStrategy strategy)
    {
        this.name = name;
        this.strategy=strategy;
    }

    public DatabaseStrategy getStrategy()
    {
        return strategy;
    }

    public void setStrategy(DatabaseStrategy strategy)
    {
        this.strategy = strategy;
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
