package src.classes;

public class Studen extends Table {

    private String name;

    @PrimaryKey
    private Integer id;

    public Studen() throws Exception
    {
        name = "Stuart";
        id = 1;
    }

    public Studen(String name, Integer id) throws Exception
    {
        this.name = name;
        this.id = id;
    }

}
