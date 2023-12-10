package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class client extends Table {

    public String name;
    public String lastName;
    public String email;

    @AutoIncrement
    @PrimaryKey
    public Integer idClient;

    public client() throws Exception {}

    public client(String name, String lastName, String email) throws Exception {
        this.name=name;
        this.lastName=lastName;
        this.email=email;
    }
}
