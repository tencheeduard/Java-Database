package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class habitat extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idHabitat;

    public Integer idType;

    public habitat() throws Exception {}
    public habitat(Integer idType) throws Exception {
        this.idType=idType;
    }
}
