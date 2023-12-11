package com.eax.petshop.classes.base;

public class DatabaseProxy extends Observable implements Observer {


    public CacheTriplet[] cachedData;

    public Database database;

    public DatabaseProxy()
    {
        cachedData = new CacheTriplet[0];
    }

    public DatabaseProxy(Database database)
    {
        cachedData = new CacheTriplet[0];
        this.database = database;
        addObserver(database);
        notifyObservers(this);
    }


    public String getDatabaseName()
    {
        return database.getName();
    }

    public DatabaseStrategy getStrategy() { return database.getStrategy(); }

    public String queryGetTables(String tableName)
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

    public CacheTriplet getCachedData(String[] input)
    {
        for(CacheTriplet data: cachedData)
        {
            if(data.input.length == input.length)
            {
                boolean equal = true;
                for(int i = 0; i < data.input.length; i++)
                {
                    if(!data.input[i].equalsIgnoreCase(input[i]))
                        equal = false;
                }
                if(equal)
                    return data;
            }
        }
        return null;
    }

    public void cache(String tableName, String[] input, String output)
    {
        CacheTriplet data = new CacheTriplet(tableName, input, output);

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
