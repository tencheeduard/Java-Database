package com.eax.petshop.tests;

import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.base.Table;
import com.eax.petshop.classes.factories.ProxyFactory;
import com.eax.petshop.classes.strategies.MySQLStrategy;

import java.io.File;
import java.util.Scanner;

public class SQLStrategyTests {


    String password;

    public SQLStrategyTests() throws Exception
    {
        File passwordFile = new File("src\\password.txt");
        password = new Scanner(passwordFile).nextLine();
    }
    public void ConnectTest()
    {
        ProxyFactory.create("db", "MySQL", "localhost", "3306", "petshop", "root", password);

        try{
            ProxyFactory.create("db", "MySQL", "localhost", "3308", "petshop" + password);
        }
        catch (Exception e)
        {
            assert true;
        }
    }

    public void MixTests() throws Exception
    {
        DatabaseProxy proxy = ProxyFactory.create("db", "MySQL", "localhost", "3306", "petshop", "root", password);
        proxy.addTable("animal", "name=TESTANIMAL");
        if(proxy.getStrategy() instanceof MySQLStrategy sqlstrat)
        {
            Table[] animals = sqlstrat.getTablesFromQuery("SELECT * FROM animal");
            assert animals[animals.length-1].getProperty("name").equals("TESTANIMAL");
        }
        proxy.removeTable("animal", "name=TESTANIMAL");

    }

}
