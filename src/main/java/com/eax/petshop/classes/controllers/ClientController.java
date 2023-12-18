package com.eax.petshop.classes.controllers;

import java.sql.Date;

public class ClientController {

    public String addClient(Controller controller, String dbName, Integer idClient, String name, String lastName, String email) throws Exception {
        String[] array = {name, lastName, email, idClient.toString()};
        return controller.addTable(dbName, "client", array);
    }

    public String removeClient(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "client", conditions);
    }

    public String getClients(Controller controller, String dbName)
    {
        return controller.table(dbName, "client");
    }
}
