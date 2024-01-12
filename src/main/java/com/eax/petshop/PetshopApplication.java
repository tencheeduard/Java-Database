package com.eax.petshop;

import com.eax.petshop.classes.UI.CLI;
import com.eax.petshop.classes.UI.REST;
import com.eax.petshop.classes.base.Observer;
import com.eax.petshop.classes.controllers.Controller;
import com.eax.petshop.tests.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class PetshopApplication {

	public static void main(String[] args) throws Exception {

		{
			// Factory Tests
			DatabaseFactoryTest.runTests();

			// Observer Tests
			ObserverTest observerTest = new ObserverTest();
			observerTest.test1();


			// SQL Tests
			SQLStrategyTests sqlTests = new SQLStrategyTests();
			sqlTests.ConnectTest();
			sqlTests.MixTests();

			// Controller Tests

			ControllerTest controllerTest = new ControllerTest();
			controllerTest.Test();

			// REST tests

			RESTTests.runTest();
		}


		Controller controller = new Controller();
		CLI cli = new CLI(controller);
		cli.start();

		if(cli.exitCode == 1)
		{
			REST rest = new REST();
			SpringApplication.run(PetshopApplication.class, args);
		}
	}
}
