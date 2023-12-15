package com.eax.petshop.classes.controllers;

import com.eax.petshop.classes.base.ArrayHelper;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.MySQLStrategy;

public class Controller {

    DatabaseProxy[] proxies;


    public DatabaseProxy getProxy(String name)
    {
        DatabaseProxy proxy = null;

        for(int i = 0; i < proxies.length; i++)
            if(proxies[i].getDatabaseName().equalsIgnoreCase(name))
            {
                proxy = proxies[i];
                break;
            }

        return proxy;
    }

    public String getCachedOutput(String dbName, String table, String function, String[] parameters)
    {
        DatabaseProxy proxy = getProxy(dbName);

        return proxy.getCachedData(table, function, parameters).output;
    }

    public String execQuery(String dbName, String query){

        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        if(proxy.database.getStrategy() instanceof MySQLStrategy sqlstrat)
            return sqlstrat.query(query);
        return "Could not create Query";
    }

    public String createDatabase(String name, String choice, String[] extraArgs)
    {
        DatabaseProxy proxy = ProxyFactory.create(name, choice, extraArgs);

        if(getProxy(name) != null)
            return "Database with that name already exists";

        if(proxy != null) {
            proxies = ArrayHelper.addElement(proxies, proxy);
            return "Created Database " + name + " with strategy " + choice;
        }
        return "Could not create Database";
    }

    public String table(String dbName, String tableName)
    {

        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        return proxy.getTablesByName(tableName);
    }

    public String addTable(String dbName, String tableName, String... extraArgs) throws Exception
    {

        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        if(extraArgs.length == 0)
            proxy.addTable(tableName);
        else
            proxy.addTable(tableName, extraArgs);

        return "Successfully added Table";
    }

    public String hasTable(String dbName, String tableName) throws Exception
    {

        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        if(proxy.hasTable(tableName))
            return "True";
        else return "False";
    }

}
