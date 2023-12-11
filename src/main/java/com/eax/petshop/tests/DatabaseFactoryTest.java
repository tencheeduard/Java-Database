package com.eax.petshop.tests;


import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.ListStrategy;

public class DatabaseFactoryTest {

    public static void runTests()
    {
        createTest();
    }

    public static void createTest()
    {
        DatabaseProxy proxy = ProxyFactory.create("db", "List");
        assert proxy.database.getName().equals("db");
        assert proxy.database.getStrategy().getClass().equals(ListStrategy.class);
        assert proxy.database.observers.length == 1;
        assert proxy.observers.length == 1;
        assert proxy.observers[0].equals(proxy.database);
        assert proxy.database.observers[0].equals(proxy);

        DatabaseProxy proxy1 = ProxyFactory.create("db", "B+ Tree");
        assert proxy1 == null;
    }
}
