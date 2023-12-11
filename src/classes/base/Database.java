package src.classes.base;

public class Database extends Observable implements Observer {

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

    public boolean add(Table table) throws Exception {
        notifyObservers(table);
        return strategy.add(table);
    }

    public boolean remove(Table table) throws Exception {
        notifyObservers(table);
        return strategy.remove(table);
    }

    public Table[] getTables(){
        return strategy.getAll();
    }

    public Table[] getTables(String tableName){
        return strategy.get(tableName);
    }

    public String getName() { return name; }

    @Override
    public void update(Object arg) {
        if(arg instanceof String str)
            notifyObservers(getTables(str));
        else if(arg instanceof DatabaseProxy proxy)
            addObserver(proxy);
    }

    @Override
    public void update(Object[] args) {

    }

    @Override
    public void update() {

    }
}
