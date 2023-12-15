package com.eax.petshop.classes.base;

public class CacheData {

    public final String table;
    public final String function;
    public final String[] parameters;

    public final String output;

    public CacheData(String table, String function, String[] parameters, String output)
    {
        this.table = table;
        this.function = function;
        this.parameters = parameters;
        this.output = output;
    }

}
