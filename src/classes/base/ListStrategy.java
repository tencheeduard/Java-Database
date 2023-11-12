package src.classes.base;

public class ListStrategy implements DbStrategy{

    @Override
    public boolean add(Table table) {
        return false;
    }

    @Override
    public boolean remove(Table table) {
        return false;
    }

    @Override
    public Table get(String name) {
        return null;
    }
}
