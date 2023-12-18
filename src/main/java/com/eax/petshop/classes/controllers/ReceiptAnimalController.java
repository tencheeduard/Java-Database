package com.eax.petshop.classes.controllers;

public class ReceiptAnimalController {

    public String addReceiptAnimal(Controller controller, String dbName, Integer idReceipt, Integer idAnimal, Integer price) throws Exception
    {
        String[] array = {idReceipt.toString(), idAnimal.toString(), price.toString()};
        return controller.addTable(dbName, "receiptanimal", array);
    }

    public String removeReceiptAnimal(Controller controller, String dbName, String... conditions) throws Exception {
        return controller.removeTable(dbName, "receiptanimal", conditions);
    }

    public String getReceiptAnimal(Controller controller, String dbName)
    {
        return controller.table(dbName, "receiptanimal");
    }
}
