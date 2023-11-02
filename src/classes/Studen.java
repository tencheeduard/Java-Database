package src.classes;

public class Studen extends Table {


    public String name;

    @PrimaryKey
    public Integer id;

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
