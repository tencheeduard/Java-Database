package src.classes.base;

public class Table1 extends Table {

    @PrimaryKey
    public Integer ID1;

    @PrimaryKey
    public Integer ID2;

    public String name;

    public Table1() throws Exception
    {
        ID1 = 1;
        ID2 = 2;
        name = "Daniel";
    }

    public Table1(int ID1, int ID2, String name) throws Exception
    {
        this.ID1 = ID1;
        this.ID2 = ID2;
        this.name = name;
    }

}
