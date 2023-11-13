package src.classes.base;

public class CacheTriplet {

    public final String table;
    public final String[] input;
    public final String output;

    public CacheTriplet(String table, String[] input, String output)
    {
        this.table = table;
        this.input = input;
        this.output = output;
    }

}
