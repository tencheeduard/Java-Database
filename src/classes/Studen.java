package src.classes;

public class Studen extends Table {

    private String name;
    private PrimaryKey<Integer> id;

    public Studen() throws Exception
    {
        name = "Stuart";
        id = new PrimaryKey<Integer>();
    }

}
