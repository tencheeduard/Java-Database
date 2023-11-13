package src.classes.controllers;

import src.classes.annotations.Cache;
import src.classes.annotations.Command;
import src.classes.base.ArrayHelper;
import src.classes.base.CacheTriplet;
import src.classes.base.DatabaseProxy;
import src.classes.factories.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Scanner;

public class CLIController {

    DatabaseProxy[] proxies;


    public CLIController()
    {
        proxies = new DatabaseProxy[0];
    }


    //Format: 'command param1 param2 param3 ...'


    @Command
    public String cdb(String[] args)
    {
        return createDatabase(args);
    }

    @Command
    public String createDatabase(String[] args)
    {

        // Format: {name, strategy}
        if(args.length < 2)
            return "Usage: createdatabase name strategy";


        String[] options = {"List"};

        String choice = "";
        DatabaseProxy proxy = null;
        for(String option: options)
        {
            if(args[1].equalsIgnoreCase(option)) {
                proxy = ProxyFactory.create(args[0], option);
                proxies = ArrayHelper.addElement(proxies, proxy);
                choice = option;
            }
        }

        if(!choice.equals("") && proxy != null)
            return "Created Database " + args[0] + " with strategy " + choice;
        return "Could not create Database";
    }

    @Command
    @Cache
    public String table(String[] args)
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: table databaseName tableName";

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]));
            {
                proxy = proxies[i];
                break;
            }
        }
        if(proxy==null)
            return "Could not find database with name " + args[0];



        return proxy.queryGetTables(args[1]);
    }

    @Command
    public String addTable(String[] args) throws Exception
    {
        // Format: {databaseName, tableName}
        if(args.length < 2)
            return "Usage: addTable databaseName tableName";

        DatabaseProxy proxy = null;
        for(int i = 0; i < proxies.length; i++)
        {
            if(proxies[i].getDatabaseName().equalsIgnoreCase(args[0]));
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

    public void invoke(String input) throws Exception
    {
        input = input.replace("-", "");
        String[] strings = input.split(" ");
        for(int i = 0; i < strings.length; i++) {
            if (strings[i].equals(""))
                strings = ArrayHelper.removeElement(strings, i--);
        }

        String output = "";

        // Search for cached output for this command


        // If no cached output is found, search for method and invoke it
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods)
            if (method.getName().equalsIgnoreCase(strings[0]) &&
                    method.isAnnotationPresent(Command.class) &&
                    method.getReturnType() == String.class)
            {
                boolean gotOutput = false;

                // Check for cached output if method is cacheable
                if(method.isAnnotationPresent(Cache.class))
                    for (DatabaseProxy proxy : proxies) {
                        CacheTriplet cachedData = proxy.getCachedData(strings);
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
