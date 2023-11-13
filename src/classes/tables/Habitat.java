package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class Habitat extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idHabitat;

    public Integer idType;

    public Habitat() throws Exception {}
    public Habitat(Integer idType) throws Exception {
        this.idType=idType;
    }
}
