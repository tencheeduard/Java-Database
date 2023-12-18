package com.eax.petshop.classes.controllers;

import java.sql.Date;

public class ReceiptController {

    public String addReceipt(Controller controller, String dbName, Integer idReceipt, Integer idClient, Date date) throws Exception
    {
        String[] array = {idReceipt.toString(), idClient.toString(), date.toString()};
        return controller.addTable(dbName, "receipt", array);
    }

    public String removeReceipt(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "receipt", conditions);
    }

    public String getReceipts(Controller controller, String dbName)
    {
        return controller.table(dbName, "receipt");
    }
}
