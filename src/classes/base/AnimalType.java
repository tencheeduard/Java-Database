package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class AnimalType extends Table{

    @PrimaryKey
    Integer idType;
    String name;

    public AnimalType(Integer idType, String name) throws Exception {
        this.idType=idType;
        this.name=name;
    }
}
