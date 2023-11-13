package src.classes.controllers;

import src.classes.annotations.Command;
import src.classes.base.ArrayHelper;
import src.classes.base.Database;
import src.classes.factories.DatabaseFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Scanner;

public class CLIController {

    Database[] databases;


    public CLIController()
    {
        databases = new Database[0];
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
        String[] options = {"List"};

        String choice = "";
        Database DB = null;
        for(String option: options)
        {
            if(args[1].equalsIgnoreCase(option)) {
                DB = DatabaseFactory.newDb(args[0], option);
                databases = ArrayHelper.addElement(databases, DB);
                choice = option;
            }
        }

        if(!choice.equals("") && DB != null)
            return "Created Database " + args[0] + " with strategy " + choice;
        return "Could not create Database";
    }

    public void invoke(String input) throws Exception
    {
        input = input.replace("-", "");
        String[] strings = input.split(" ");
        for(int i = 0; i < strings.length; i++)
        {
            if(strings[i].equals(""))
                strings = ArrayHelper.removeElement(strings, i--);
        }

        Method[] methods = getClass().getDeclaredMethods();

        for(Method method: methods)
        {
            if(method.getName().equalsIgnoreCase(strings[0]) && method.isAnnotationPresent(Command.class))
            {
                System.out.println(method.invoke(this, (Object) ArrayHelper.removeElement(strings, 0)));
                return;
            }
        }

        System.out.println("Could not create database");
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
