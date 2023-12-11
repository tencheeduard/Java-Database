package com.eax.petshop.classes.controllers;

import com.eax.petshop.classes.base.ArrayHelper;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.MySQLStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Scanner;

@RestController
public class RESTController {

    DatabaseProxy proxy;


    String password;

    public RESTController()
    {
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

    @GetMapping("/connect")
    public String connect(
            @RequestParam(value = "address", defaultValue = "localhost") String address,
            @RequestParam(value = "port", defaultValue = "3306") String port,
            @RequestParam(value = "dbName", defaultValue = "petshop") String dbName,
            @RequestParam(value = "user", defaultValue = "root") String user
    ) throws Exception {

        try{
            proxy = ProxyFactory.create("db", "MySQL", address, port, dbName, user, password);

            if(proxy.database.getStrategy() instanceof MySQLStrategy sqlStrategy)
            {
                if(sqlStrategy.connection == null || !sqlStrategy.connection.isValid(5))
                    throw new Exception("could not connect to database");
            }

            return "connected to database";
        }
        catch (Exception e)
        {
            return e.toString();
        }

    }

    @GetMapping("/print")
    public String print(
            @RequestParam(value = "message", defaultValue = "Hello World!") String message
    ) throws Exception {
        return message;
    }

    @GetMapping("/query")
    public String query(
            @RequestParam(value = "query", defaultValue = "show tables from petshop;") String query
    ) throws Exception {
        if(proxy.database.getStrategy() instanceof MySQLStrategy sqlstrat)
            return sqlstrat.getQueryResults(query);
        return "Could not create Query";
    }

    @GetMapping("/addTable")
    public String addTable(
            @RequestParam(value = "tableName") String tableName
    ) throws Exception {
        try {
            proxy.addTable(tableName);
            return "Added table";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

}
