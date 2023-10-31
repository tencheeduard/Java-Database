package src.classes;

public class Studen extends Table {

    private String name;
    private PrimaryKey<Integer> id;

    public Studen() throws Exception
    {
        name = "Stuart";
        id = new PrimaryKey<Integer>();
    }

    public Studen(String name, Integer id) throws Exception
    {
        this.name = name;
        this.id = new PrimaryKey<Integer>(id);
    }

}
