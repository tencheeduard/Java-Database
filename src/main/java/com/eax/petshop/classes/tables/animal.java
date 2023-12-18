package com.eax.petshop.classes.tables;

import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

import java.sql.Date;

public class animal extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idAnimal;

    public String name;
    public Date date;
    public Integer animalType;

    public animal() throws Exception {}
}
