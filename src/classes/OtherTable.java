package src.classes;

public class OtherTable extends Table{

    public String name;

    @PrimaryKey
    public Integer id;

    public OtherTable() throws Exception
    {
        name = "Stuart";
        id = 1;
    }

    public OtherTable(String name, Integer id) throws Exception
    {
        this.name = name;
        this.id = id;
    }
}
