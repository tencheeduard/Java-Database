package com.eax.petshop.classes.controllers;

public class HabitatController {

    public String addHabitat(Controller controller, String dbName, Integer idHabitat, Integer idType) throws Exception
    {
        String[] array = {idHabitat.toString(), idType.toString()};
        return controller.addTable(dbName, "habitat", array);
    }

    public String removeHabitat(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "habitat", conditions);
    }

    public String getHabitats(Controller controller, String dbName)
    {
        return controller.table(dbName, "habitat");
    }
}
