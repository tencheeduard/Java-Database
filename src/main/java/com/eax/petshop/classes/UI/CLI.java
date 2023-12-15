package com.eax.petshop.classes.UI;

import com.eax.petshop.classes.annotations.CLICommand;
import com.eax.petshop.classes.base.ArrayHelper;
import com.eax.petshop.classes.base.CacheData;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.controllers.Controller;
import com.eax.petshop.classes.factories.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Scanner;

public class CLI {

    Controller controller;

    public CLI(Controller controller)
    {
        this.controller = controller;
    }


    // Base Invoke Command
    public void invoke(String input) throws Exception
    {
        input = input.replace("-", "");
        String[] strings = input.split(" ");
        for(int i = 0; i < strings.length; i++)
            if (strings[i].equals(""))
                strings = ArrayHelper.removeElement(strings, i--);

        String output = "";

        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods)
            if (method.getName().equalsIgnoreCase(strings[0]) &&
                    method.isAnnotationPresent(CLICommand.class) &&
                    method.getReturnType() == String.class)
                output = (String) method.invoke(this, (Object) ArrayHelper.removeElement(strings, 0));

        System.out.println(output);
    }


    // Start the CLI
    public void start() throws Exception{
        Scanner scanner;
        while(true)
        {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            invoke(input);
        }
    }


    // Commands

    @CLICommand
    public String execQuery(String[] args) {
        String name = args[0];

        // Args: {dbName, query...}

        String query ="";
        for (int i = 1; i < args.length; i++) {
            query += args[i];
            if (i < args.length - 1)
                query += " ";
        }

        return controller.execQuery(name, query);
    }

    @CLICommand
    public String createDatabase(String[] args)
    {

        // Format: {name, strategy, args}
        if(args.length < 2)
            return "Usage: createdatabase name strategy extraArgs...";

        String[] options = ProxyFactory.options();

        String choice = "";
        for(String option: options)
            if(args[1].equalsIgnoreCase(option)) {
                choice = option;
                break;
            }

        return controller.createDatabase(args[0], choice, ArrayHelper.clone(args,2));
    }

    @CLICommand
    public String cdb(String[] args) { return createDatabase(args); }

    @CLICommand
    public String table(String[] args)
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: table databaseName tableName";

        return controller.table(args[0], args[1]);
    }

    @CLICommand
    public String addTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: addTable databaseName tableName";

        return controller.addTable(args[0], args[1], ArrayHelper.clone(args, 2));
    }

    @CLICommand
    public String hasTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: hasTable databaseName tableName";

        return controller.hasTable(args[0], args[1]);
    }

}
