package com.eax.petshop.classes.controllers;

import com.eax.petshop.classes.base.ArrayHelper;
import com.eax.petshop.classes.base.CacheData;
import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.base.Table;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.MySQLStrategy;

public class Controller {

    public DatabaseProxy[] proxies;


    public Controller()
    {
        proxies = new DatabaseProxy[0];
    }

    public DatabaseProxy getProxy(String name)
    {
        DatabaseProxy proxy = null;

        for (DatabaseProxy p : proxies)
            if (p.getDatabaseName().equalsIgnoreCase(name)) {
                proxy = p;
                break;
            }

        return proxy;
    }

    public String getCachedOutput(DatabaseProxy proxy, String table, String function, String[] parameters)
    {
        CacheData data = proxy.getCachedData(table, function, parameters);
        if(data!=null)
            return data.output;
        return null;
    }

    public void cache(DatabaseProxy proxy, String table, String function, String[] parameters, String output)
    {
        proxy.cache(table, function, parameters, output);
    }

    public String execQuery(String dbName, String query)
    {
        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        if(proxy.database.getStrategy() instanceof MySQLStrategy sqlstrat)
            return sqlstrat.query(query);
        return "Could not create Query";
    }

    public String createDatabase(String name, String choice, String... extraArgs)
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

        // Caching
        String[] cacheParameters = {dbName, tableName};
        String cachedOutput = getCachedOutput(proxy, tableName, "table", cacheParameters);

        if(cachedOutput!=null)
            return cachedOutput;

        String output = proxy.getTablesByName(tableName);
        cache(proxy, tableName, "table", cacheParameters, output);


        return output;
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

    public String removeTable(String dbName, String tableName, String... extraArgs) throws Exception
    {
        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        proxy.removeTable(tableName, extraArgs);

        return "Removing tables";

    }

    public String hasTable(String dbName, String tableName)
    {

        DatabaseProxy proxy = getProxy(dbName);
        if(proxy==null)
            return "Could not find database with name " + dbName;

        if(proxy.hasTable(tableName))
            return "True";
        else return "False";
    }

}
