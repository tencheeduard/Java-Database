package com.eax.petshop.classes.base;

import java.util.Arrays;

public class DatabaseProxy extends Observable implements Observer {


    public CacheData[] cachedData;

    public Database database;

    public DatabaseProxy()
    {
        cachedData = new CacheData[0];
    }

    public DatabaseProxy(Database database)
    {
        cachedData = new CacheData[0];
        this.database = database;
        addObserver(database);
        notifyObservers(this);
    }


    public String getDatabaseName()
    {
        return database.getName();
    }

    public DatabaseStrategy getStrategy() { return database.getStrategy(); }


    public String getTablesByName(String tableName)
    {
        Table[] tables = database.getTables(tableName);
        if(tables.length == 0)
            return "No tables";

        String output = "";
        for(Table t: tables)
            output += t.toString() + '\n';

        return output;
    }

    public void addTable(String tableName) throws Exception
    {
        Class<?> clazz = Class.forName("com.eax.petshop.classes.tables." + tableName);

        if(clazz.getDeclaredConstructor().newInstance() instanceof Table table)
        {
            database.add(table);
        }
    }

    public void addTable(String tableName, String... params) throws Exception
    {
        Class<?> clazz = Class.forName("com.eax.petshop.classes.tables." + tableName);

        Table table;
        if(clazz.getDeclaredConstructor().newInstance() instanceof Table t)
            table = t;
        else
            throw new Exception("Table with that name does not exist.");

        boolean indexed = false;
        for(String param: params)
            if(!param.contains("=")) {
                indexed = true;
                break;
            }

        if(indexed)
            for(int i = 0; i < params.length; i++)
            {
                Object val = params[i];
                if(table.getField(i).getType() != String.class)
                    val = StringConverter.convert(params[i], table.getField(i).getType());

                table.setProperty(i, val);
            }
        else
            for(String param: params)
            {
                String[] splitString = param.split("=");

                Object val = splitString[1];
                if(table.getField(splitString[0]).getType() != String.class)
                    val = StringConverter.convert(splitString[1], table.getField(splitString[0]).getType());

                table.setProperty(splitString[0], val);
            }


        database.add(table);
    }

    public CacheData getCachedData(String tableName, String function, String[] parameters)
    {
        for(CacheData data: cachedData)
            if(data.table.equals(tableName) && data.function.equals(function) && Arrays.equals(data.parameters,parameters))
                return data;
        return null;
    }

    public void cache(String tableName, String function, String[] parameters, String output)
    {
        CacheData data = new CacheData(tableName, function, parameters, output);

        cachedData = ArrayHelper.addElement(cachedData, data);
    }

    public boolean hasTable(String tableName)
    {
        Table[] tables = database.getTables();

        for(Table table: tables)
            if(table.getClass().getSimpleName().equals(tableName))
                return true;
        return false;
    }


    @Override
    public void update(Object arg) {
        if(arg instanceof Table table)
            for(int i = 0; i < cachedData.length; i++)
                if(cachedData[i].table.equals(table.getClass().getSimpleName()))
                    cachedData = ArrayHelper.removeElement(cachedData, i--);
    }

    @Override
    public void update(Object[] args) {

    }

    @Override
    public void update() {

    }

}
