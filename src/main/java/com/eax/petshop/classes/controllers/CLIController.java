package com.eax.petshop.classes.controllers;

import com.eax.petshop.classes.annotations.Cache;
import com.eax.petshop.classes.annotations.CLICommand;
import com.eax.petshop.classes.base.ArrayHelper;
import com.eax.petshop.classes.base.CacheData;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.MySQLStrategy;

import java.lang.reflect.Method;
import java.util.Scanner;

public class CLIController {

    DatabaseProxy[] proxies;


    public CLIController()
    {
        proxies = new DatabaseProxy[0];
    }


    //Format: 'command param1 param2 param3 ...'


    @CLICommand
    public String execQuery(String[] args){

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]))
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];

        String query ="";
        for (int i = 1; i < args.length; i++) {
            query += args[i];
            if (i < args.length - 1)
                query += " ";
        }

        if(proxy.database.getStrategy() instanceof MySQLStrategy sqlstrat)
            return sqlstrat.query(query);
        return "Could not create Query";
    }

    @CLICommand
    public String cdb(String[] args)
    {
        return createDatabase(args);
    }

    @CLICommand
    public String createDatabase(String[] args)
    {

        // Format: {name, strategy, args}
        if(args.length < 2)
            return "Usage: createdatabase name strategy extraArgs...";

        String[] options = ProxyFactory.options();

        String choice = "";
        DatabaseProxy proxy = null;
        for(String option: options)
        {
            if(args[1].equalsIgnoreCase(option)) {
                proxy = ProxyFactory.create(args[0], option, ArrayHelper.clone(args,2));
                proxies = ArrayHelper.addElement(proxies, proxy);
                choice = option;
            }
        }

        if(!choice.equals("") && proxy != null)
            return "Created Database " + args[0] + " with strategy " + choice;
        return "Could not create Database";
    }

    @CLICommand
    @Cache
    public String table(String[] args)
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: table databaseName tableName";

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]))
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];



        return proxy.getTablesByName(args[1]);
    }

    @CLICommand
    public String addTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: addTable databaseName tableName";

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]))
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];

        proxy.addTable(args[1]);

        return "Added Table";
    }

    @CLICommand
    public String clearCache(String[] args)
    {
        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]))
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];

        proxy.cachedData = new CacheData[0];

        return "deleted";
    }

    @CLICommand
    public String hasTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: hasTable databaseName tableName";

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]))
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];

        if(proxy.hasTable(args[1]))
            return "True";
        else return "False";
    }

    public void invoke(String input) throws Exception
    {
        input = input.replace("-", "");
        String[] strings = input.split(" ");
        for(int i = 0; i < strings.length; i++)
            if (strings[i].equals(""))
                strings = ArrayHelper.removeElement(strings, i--);

        String output = "";

        // Search for cached output for this command


        // If no cached output is found, search for method and invoke it
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods)
            if (method.getName().equalsIgnoreCase(strings[0]) &&
                    method.isAnnotationPresent(CLICommand.class) &&
                    method.getReturnType() == String.class)
            {
                boolean gotOutput = false;

                // Check for cached output if method is cacheable
                if(method.isAnnotationPresent(Cache.class))
                    for (DatabaseProxy proxy : proxies) {
                        CacheData cachedData = proxy.getCachedData(strings);
                        if (cachedData != null) {
                            output = cachedData.output;
                            gotOutput = true;
                        }
                    }
                if(!gotOutput)
                    output = (String) method.invoke(this, (Object) ArrayHelper.removeElement(strings, 0));

                // Cache output
                if(method.isAnnotationPresent(Cache.class))
                    for (String str : strings)
                        for (DatabaseProxy proxy : proxies)
                            if (proxy.hasTable(str))
                                proxy.cache(str, strings, output);
            }

        System.out.println(output);
    }

    public void start() throws Exception{
        Scanner scanner;
        while(true)
        {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            invoke(input);
        }
    }

}
