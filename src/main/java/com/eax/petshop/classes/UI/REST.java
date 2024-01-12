package com.eax.petshop.classes.UI;

import com.eax.petshop.classes.controllers.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.sql.Date;
import java.util.Scanner;

@RestController
public class REST {

    Controller controller;
    AnimalController animalController;
    AnimalTypeController animalTypeController;
    ClientController clientController;
    HabitatController habitatController;
    ReceiptAnimalController receiptAnimalController;
    ReceiptController receiptController;

    public REST()
    {
        controller = new Controller();
        animalController = new AnimalController();
        animalTypeController = new AnimalTypeController();
        clientController = new ClientController();
        habitatController = new HabitatController();
        receiptAnimalController = new ReceiptAnimalController();
        receiptController = new ReceiptController();
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
        }

    @GetMapping("/print")
    public String print(
            @RequestParam(value = "message", defaultValue = "Hello World!") String message
    ) throws Exception {
        return message;
    }

    @GetMapping("/connect")
    public String print(
            @RequestParam(value = "dbName", defaultValue = "db") String dbName,
            @RequestParam(value = "address", defaultValue = "localhost") String address,
            @RequestParam(value = "port", defaultValue = "3306") String port,
            @RequestParam(value = "mySqlDB", defaultValue = "petshop") String mySqlDB,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) throws Exception {
        String[] args = { address, port, mySqlDB, username, password  };
        return controller.createDatabase(dbName, "MySQL", args);
    }

    @GetMapping("/execQuery")
    public String query(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "query") String query
    ) throws Exception{
        return controller.execQuery(dbName, query);
    }

    @GetMapping("/createDatabase")
    public String createDatabase(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "arguments", defaultValue = "") String arguments
    ) throws Exception {
        // Arguments format = "arg1 arg2 arg3 arg4 arg5"
        String[] args = new String[0];
        if(!arguments.isEmpty())
            args = arguments.split(" ");

        return controller.createDatabase(dbName, type, args);
    }

    @GetMapping("/table")
    public String table(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "tableName") String tableName
    ) throws Exception {
        return controller.table(dbName, tableName);
    }

    @GetMapping("/addTable")
    public String addTable(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "arguments", defaultValue = "") String arguments
    ) throws Exception {
        // Arguments format = "arg1 arg2 arg3 arg4 arg5"
        String[] args = new String[0];
        if(!arguments.isEmpty())
            args = arguments.split(" ");

        return controller.addTable(dbName, type, args);
    }

    @GetMapping("/removeTable")
    public String removeTable(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "arguments", defaultValue = "") String arguments
    ) throws Exception {
        // Arguments format = "arg1 arg2 arg3 arg4 arg5"
        String[] args = new String[0];
        if(!arguments.isEmpty())
            args = arguments.split(" ");

        return controller.removeTable(dbName, type, args);
    }

    @GetMapping("/hasTable")
    public String hasTable(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "tableName") String tableName
    ) throws Exception {
        return controller.hasTable(dbName, tableName);
    }

    // TABLE-SPECIFIC


    // Animal
    @GetMapping("/addAnimal")
    public String addAnimal(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idAnimal", defaultValue = "0") Integer idAnimal,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "animalType") Integer animalType
    ) throws Exception {
        Date dt = Date.valueOf(date);
        return animalController.addAnimal(controller, dbName, idAnimal, name, dt, animalType);
    }
    @GetMapping("/removeAnimal")
    public String removeAnimal(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return animalController.removeAnimal(controller, dbName, conditions);
    }

    @GetMapping("/getAnimals")
            public String getAnimals(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return animalController.getAnimals(controller, dbName);
    }

    // AnimalType
    @GetMapping("/addAnimalType")
    public String addAnimalType(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idAnimalType", defaultValue = "0") Integer idType,
            @RequestParam(value = "name") String name
    ) throws Exception {
        return animalTypeController.addAnimalType(controller, dbName, idType, name);
    }
    @GetMapping("/removeAnimalType")
    public String removeAnimalType(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return animalTypeController.removeAnimalType(controller, dbName, conditions);
    }

    @GetMapping("/getAnimalTypes")
    public String getAnimalTypes(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return animalTypeController.getAnimalTypes(controller, dbName);
    }

    // Client
    @GetMapping("/addClient")
    public String addClient(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idClient", defaultValue = "0") Integer idClient,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "email") String email
    ) throws Exception {
        return clientController.addClient(controller, dbName, idClient, name, lastName, email);
    }
    @GetMapping("/removeClient")
    public String removeClient(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return clientController.removeClient(controller, dbName, conditions);
    }

    @GetMapping("/getClients")
    public String getClients(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return clientController.getClients(controller, dbName);
    }

    // Habitat
    @GetMapping("/addHabitat")
    public String addHabitat(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idHabitat", defaultValue = "0") Integer idHabitat,
            @RequestParam(value = "idType") Integer idType
    ) throws Exception {
        return habitatController.addHabitat(controller, dbName, idHabitat, idType);
    }
    @GetMapping("/removeHabitat")
    public String removeHabitat(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return habitatController.removeHabitat(controller, dbName, conditions);
    }

    @GetMapping("/getHabitats")
    public String getHabitats(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return habitatController.getHabitats(controller, dbName);
    }

    // Receipt
    @GetMapping("/addReceipt")
    public String addReceipt(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idReceipt", defaultValue = "0") Integer idReceipt,
            @RequestParam(value = "idClient") Integer idClient,
            @RequestParam(value = "date") String date

    ) throws Exception {
        Date dt = Date.valueOf(date);
        return receiptController.addReceipt(controller, dbName, idReceipt, idClient, dt);
    }
    @GetMapping("/removeReceipt")
    public String removeReceipt(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return receiptController.removeReceipt(controller, dbName, conditions);
    }

    @GetMapping("/getReceipts")
    public String getReceipts(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return receiptController.getReceipts(controller, dbName);
    }

    // ReceiptAnimal
    @GetMapping("/addReceiptAnimal")
    public String addReceiptAnimal(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "idReceipt") Integer idReceipt,
            @RequestParam(value = "idAnimal") Integer idAnimal,
            @RequestParam(value = "price") Integer price

    ) throws Exception {
        return receiptAnimalController.addReceiptAnimal(controller, dbName, idReceipt, idAnimal, price);
    }
    @GetMapping("/removeReceiptAnimal")
    public String removeReceiptAnimal(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "conditions", defaultValue = "") String conditions
    ) throws Exception {
        return receiptAnimalController.removeReceiptAnimal(controller, dbName, conditions);
    }

    @GetMapping("/getReceiptAnimals")
    public String getReceiptAnimals(
            @RequestParam(value = "dbName") String dbName
    ) throws Exception {
        return receiptAnimalController.getReceiptAnimal(controller, dbName);
    }
}
