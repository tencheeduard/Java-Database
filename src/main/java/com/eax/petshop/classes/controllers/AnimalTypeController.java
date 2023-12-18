package com.eax.petshop.classes.controllers;

import java.sql.Date;

public class AnimalTypeController {

    public String addAnimalType(Controller controller, String dbName, Integer idType, String name) throws Exception
    {
        String[] array = {idType.toString(), name};
        return controller.addTable(dbName, "animaltype", array);
    }

    public String removeAnimalType(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "animaltype", conditions);
    }

    public String getAnimalTypes(Controller controller, String dbName)
    {
        return controller.table(dbName, "animaltype");
    }
}
