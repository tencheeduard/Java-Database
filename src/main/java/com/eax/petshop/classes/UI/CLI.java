package com.eax.petshop.classes.UI;

import com.eax.petshop.classes.annotations.CLICommand;
import com.eax.petshop.classes.helpers.ArrayHelper;
import com.eax.petshop.classes.controllers.Controller;
import com.eax.petshop.classes.factories.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Scanner;

public class CLI {

    Controller controller;

    boolean stop;

    public int exitCode;

    public boolean printStackTrace = true;



    public CLI(Controller controller)
    {
        this.controller = controller;
    }


    // Base Invoke Command
    public void invoke(String input)
    {
        input = input.replace("-", "");
        String[] strings = input.split(" ");
        for(int i = 0; i < strings.length; i++)
            if (strings[i].equals(""))
                strings = ArrayHelper.removeElement(strings, i--);

        String output = "";

        try {
            Method[] methods = getClass().getDeclaredMethods();
            for (Method method : methods)
                if (method.getName().equalsIgnoreCase(strings[0]) &&
                        method.isAnnotationPresent(CLICommand.class) &&
                        method.getReturnType() == String.class)
                    output = (String) method.invoke(this, (Object) ArrayHelper.removeElement(strings, 0));

            System.out.println(output);
        }
        catch (Exception e)
        {
            System.out.println("Could not invoke command");
            if(printStackTrace)
                e.printStackTrace();
        }
    }


    // Start the CLI
    public void start(){
        Scanner scanner;
        while(!stop)
        {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            invoke(input);
        }
    }

    @CLICommand
    public String tgs(String[] args)
    {
        return toggleStackTrace(args);
    }

    @CLICommand
    public String exit(String[] args)
    {
        stop = true;
        if(args.length > 0)
            exitCode = Integer.parseInt(args[0]);
        else exitCode = 0;
        return "Exiting CLI with code " + exitCode + "...";
    }

    @CLICommand
    public String toggleStackTrace(String[] args)
    {
        if(args.length > 0) {
            if (args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("1"))
                printStackTrace = true;
            else if (args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("0"))
                printStackTrace = false;
        }
        else printStackTrace = !printStackTrace;
        if(printStackTrace)
            return "Now printing full Stack Trace";
        else
            return "No longer printing full Stack Trace";
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
            return "Usage: addTable databaseName tableName extraArgs";

        return controller.addTable(args[0], args[1], ArrayHelper.clone(args, 2));
    }

    @CLICommand
    public String removeTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: removeTable databaseName tableName extraArgs";

        return controller.removeTable(args[0], args[1], ArrayHelper.clone(args, 2));
    }

    @CLICommand
        public String hasTable(String[] args)
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: hasTable databaseName tableName";

        return controller.hasTable(args[0], args[1]);
    }

}
