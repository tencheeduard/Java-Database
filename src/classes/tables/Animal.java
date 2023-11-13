package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class Animal extends Table {

    public String name;

    @AutoIncrement
    @PrimaryKey
    public Integer idAnimal;
    public String date;

    public Animal() throws Exception {}

    public Animal(String name, String date) throws Exception {
        this.name=name;
        this.date=date;
    }
}
