package com.eax.petshop.tests;

import com.eax.petshop.classes.UI.REST;
import com.eax.petshop.classes.controllers.*;

import java.sql.Date;

public class RESTTests {
    public static void runTest() throws Exception
    {
        Controller controller = new Controller();
        AnimalController animalController = new AnimalController();
        AnimalTypeController animalTypeController = new AnimalTypeController();
        ClientController clientController = new ClientController();
        HabitatController habitatController = new HabitatController();
        ReceiptAnimalController receiptAnimalController = new ReceiptAnimalController();
        ReceiptController receiptController = new ReceiptController();

        controller.createDatabase("db", "List");

        //Animal

        animalController.addAnimal(controller, "db", 0, "pomel", Date.valueOf("1980-01-01"), 1);
        assert controller.hasTable("db", "animal").equals("True");
        assert controller.getProxy("db").getStrategy().get("animal").length == 1;

        animalController.addAnimal(controller, "db", 0, "pomel", Date.valueOf("1980-01-01"), 1);
        animalController.addAnimal(controller, "db", 0, "pomel", Date.valueOf("1980-01-01"), 1);
        assert controller.getProxy("db").getStrategy().get("animal").length == 3;

        animalController.removeAnimal(controller, "db");
        assert controller.getProxy("db").getStrategy().get("animal").length == 0;

        // Animal Type

        animalTypeController.addAnimalType(controller, "db", 0, "cat");
        assert controller.hasTable("db", "animaltype").equals("True");
        assert controller.getProxy("db").getStrategy().get("animaltype").length == 1;

        animalTypeController.addAnimalType(controller, "db", 0, "cat");
        animalTypeController.addAnimalType(controller, "db", 0, "dog");
        assert controller.getProxy("db").getStrategy().get("animaltype").length == 3;

        animalTypeController.removeAnimalType(controller, "db", "name=cat");
        assert controller.getProxy("db").getStrategy().get("animaltype").length == 1;

        animalTypeController.removeAnimalType(controller, "db");
        assert controller.getProxy("db").getStrategy().get("animaltype").length == 0;


        // Client

        clientController.addClient(controller, "db", 0, "name", "lastname", "email");
        assert controller.hasTable("db", "client").equals("True");
        assert controller.getProxy("db").getStrategy().get("client").length == 1;

        clientController.addClient(controller, "db", 0, "name", "lastname", "email");
        clientController.addClient(controller, "db", 0, "name", "lastname", "email");

        assert controller.getProxy("db").getStrategy().get("client").length == 3;

        clientController.removeClient(controller, "db");
        assert controller.getProxy("db").getStrategy().get("client").length == 0;

        // Habitat

        habitatController.addHabitat(controller, "db", 0, 0);
        assert controller.hasTable("db", "habitat").equals("True");
        assert controller.getProxy("db").getStrategy().get("habitat").length == 1;

        habitatController.addHabitat(controller, "db", 0, 0);
        habitatController.addHabitat(controller, "db", 0, 0);
        assert controller.getProxy("db").getStrategy().get("habitat").length == 3;
    }
}
