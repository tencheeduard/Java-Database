package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class Animal extends Table{

    String name;
    @PrimaryKey
    Integer idAnimal;
    String date;

    public Animal(String name, Integer idAnimal, String date) throws Exception {
        this.name=name;
        this.idAnimal= idAnimal;
        this.date=date;
    }
}
