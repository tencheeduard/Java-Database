package com.eax.petshop.classes.tables;


import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

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
