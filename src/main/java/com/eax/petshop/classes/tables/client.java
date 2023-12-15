package com.eax.petshop.classes.tables;


import com.eax.petshop.classes.annotations.AutoIncrement;
import com.eax.petshop.classes.annotations.PrimaryKey;
import com.eax.petshop.classes.base.Table;

public class client extends Table {

    public String name;
    public String lastName;
    public String email;

    @AutoIncrement
    @PrimaryKey
    public Integer idClient;

    public client() throws Exception {}
}
