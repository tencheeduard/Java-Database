package com.eax.petshop.classes.tables;


import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

public class animaltype extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idType;
    public String name;

    public animaltype() throws Exception {
    }
}