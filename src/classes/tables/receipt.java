package src.classes.tables;

import src.classes.annotations.AutoIncrement;
import src.classes.annotations.PrimaryKey;
import src.classes.base.Table;

public class receipt extends Table {

    public Integer idClient;

    @AutoIncrement
    @PrimaryKey
    public Integer idReceipt;
    public String date;

    public receipt() throws Exception {}
    public receipt(Integer idClient, String date) throws Exception {
        this.idClient=idClient;
        this.date=date;
    }
}
