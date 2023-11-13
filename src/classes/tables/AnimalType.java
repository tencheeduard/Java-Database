package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class AnimalType extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idType;
    public String name;

    public AnimalType() throws Exception {}

    public AnimalType(String name) throws Exception {
        this.name=name;
    }
}
