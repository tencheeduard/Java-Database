package src.classes.base;

import src.classes.annotations.PrimaryKey;

public class Receipt extends Table{

    Integer idClient;
    @PrimaryKey
    Integer idReceipt;
    String date;
    public Receipt(Integer idClient, Integer idReceipt, String date) throws Exception {
        this.idClient=idClient;
        this.idReceipt=idReceipt;
        this.date=date;
    }
}
