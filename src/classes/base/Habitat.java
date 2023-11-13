package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class Habitat extends Table{

    @PrimaryKey
    Integer idHabitat;
    @PrimaryKey
    Integer idType;
    public Habitat(Integer idType, Integer idHabitat) throws Exception {
        this.idHabitat=idHabitat;
        this.idType=idType;
    }
}
