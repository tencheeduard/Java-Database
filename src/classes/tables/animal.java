package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class animal extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idAnimal;

    public String name;

    public String date;

    public animal() throws Exception {}

    public animal(String name, String date) throws Exception {
        this.name=name;
        this.date=date;
    }
}
