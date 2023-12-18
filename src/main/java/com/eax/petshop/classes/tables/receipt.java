package com.eax.petshop.classes.tables;

import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

import java.sql.Date;

public class receipt extends Table {

    public Integer idClient;

    @AutoIncrement
    @PrimaryKey
    public Integer idReceipt;
    public Date date;

    public receipt() throws Exception {}
}
