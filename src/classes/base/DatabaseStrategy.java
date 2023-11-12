package src.classes.base;

public interface DatabaseStrategy {

    public boolean add(Table table) throws Exception;
    public boolean remove(Table table) throws Exception;
    public Table[] get(String name);
    
    public boolean contains(Repo<?> repo);
    public Table[] getAll();

}
