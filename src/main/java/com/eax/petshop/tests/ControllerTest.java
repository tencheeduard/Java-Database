package com.eax.petshop.tests;

import com.eax.petshop.classes.controllers.Controller;

public class ControllerTest {

    Controller controller;

    public ControllerTest()
    {
        controller = new Controller();
    }

    public void Test() throws Exception
    {
        assert controller.proxies.length == 0;

        controller.createDatabase("db", "List");
        assert controller.proxies.length == 1;

        controller.addTable("db", "animal");
        assert controller.hasTable("db", "animal").equals("True");
        assert controller.hasTable("db", "animaltype").equals("False");
        assert !controller.table("db", "animal").equals("No tables");

        controller.removeTable("db", "animal");
        assert controller.table("db", "animal").equals("No tables");
    }

}
