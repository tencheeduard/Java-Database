package src.classes.base;

public interface DbStrategy {

    public boolean add(Table table);
    public boolean remove(Table table);
    public Table get(String name);

}
