package com.eax.petshop.classes.tables;

import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

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
