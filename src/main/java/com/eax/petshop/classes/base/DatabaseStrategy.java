package com.eax.petshop.classes.base;

public interface DatabaseStrategy {

    boolean add(Table table) throws Exception;
    boolean remove(Table table) throws Exception;
    Table[] get(String name);
    Table[] getAll();

}
