package src;

import src.classes.base.Database;
import src.classes.base.DatabaseProxy;
import src.classes.controllers.CLIController;
import src.classes.strategies.ListStrategy;

import javax.xml.crypto.Data;

public class App {
    public static void main(String[] args) throws Exception
    {
        CLIController cli = new CLIController();

        cli.invoke("cdb db list");
        cli.invoke("addtable db Table1");
        cli.invoke("addtable db Table1");

        cli.start();

    }
}