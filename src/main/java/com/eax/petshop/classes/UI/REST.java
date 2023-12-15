package com.eax.petshop.classes.UI;

import com.eax.petshop.classes.controllers.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Scanner;

@RestController
public class REST {

    String password;

    Controller controller;

    public REST()
    {
        controller = new Controller();
        try {
            File passwordFile = new File("src\\password.txt");
            password = new Scanner(passwordFile).nextLine();
        }
        catch(Exception e)
        {
            try {
                print(e.toString());
            }
            catch (Exception ex) { }
        }
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

    @GetMapping("/hasTable")
    public String hasTable(
            @RequestParam(value = "dbName") String dbName,
            @RequestParam(value = "tableName") String tableName
    ) throws Exception {
        return controller.hasTable(dbName, tableName);
    }

}
