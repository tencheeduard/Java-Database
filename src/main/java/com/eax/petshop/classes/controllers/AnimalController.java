package com.eax.petshop.classes.controllers;

import com.eax.petshop.classes.base.DatabaseProxy;
import jdk.jshell.spi.ExecutionControlProvider;

import java.sql.Date;

public class AnimalController {

    public String addAnimal(Controller controller, String dbName, Integer idAnimal, String name, Date date, Integer animalType) throws Exception
    {
        String[] array = {String.valueOf(idAnimal), name, String.valueOf(date), String.valueOf(animalType)};
        return controller.addTable(dbName, "animal", array);
    }

    public String removeAnimal(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "animal", conditions);
    }

    public String getAnimals(Controller controller, String dbName)
    {
        return controller.table(dbName, "animal");
    }

}
