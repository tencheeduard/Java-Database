package src.classes.base;

public class Table1 extends Table {

    @PrimaryKey
    public Integer ID;

    @PrimaryKey
    public String name;

    public Table1() throws Exception
    {
        ID = 1;
        name = "Daniel";
    }

    public Table1(int ID, String name) throws Exception
    {
        this.ID = ID;
        this.name = name;
    }

}
