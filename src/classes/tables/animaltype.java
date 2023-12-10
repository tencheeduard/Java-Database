package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class animaltype extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idType;
    public String name;

    public animaltype() throws Exception {}

    public animaltype(String name) throws Exception {
        this.name=name;
    }
}
