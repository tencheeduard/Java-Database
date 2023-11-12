package src;

import src.classes.controllers.CLIController;

public class App {
    public static void main(String[] args) throws Exception
    {
        CLIController cli = new CLIController();

        cli.start();
    }
}
