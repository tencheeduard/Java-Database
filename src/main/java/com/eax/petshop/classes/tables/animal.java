package com.eax.petshop.classes.tables;

import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

public class animal extends Table {

    @AutoIncrement
    @PrimaryKey
    public Integer idAnimal;

    public String name;

    public String date;

    public animal() throws Exception {}

    public animal(String name, String date) throws Exception {
        this.name=name;
        this.date=date;
    }
}
