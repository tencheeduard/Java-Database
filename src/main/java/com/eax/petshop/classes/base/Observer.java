package com.eax.petshop.classes.base;

public interface Observer {


    void update(Object arg);
    void update(Object[] args);
    void update();

}