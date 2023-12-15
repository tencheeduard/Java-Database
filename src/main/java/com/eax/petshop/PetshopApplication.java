package com.eax.petshop;

import com.eax.petshop.classes.base.DatabaseProxy;
import com.eax.petshop.classes.controllers.CLIController;
import com.eax.petshop.classes.factories.ProxyFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class PetshopApplication {

	public static void main(String[] args) throws Exception{
		///*
		CLIController cli = new CLIController();

		File passwordFile = new File("src\\password.txt");
		String password = new Scanner(passwordFile).nextLine();

		cli.invoke("cdb db mysql localhost 3306 petshop root " + password);

		DatabaseProxy proxy = ProxyFactory.create("dbName", "List");

		proxy.addTable("animal", "date=1490-11-11", "name=tomel");
		proxy.addTable("animal", "0", "1490-11-11", "tomel");

		System.out.println(proxy.getTablesByName("animal"));

		cli.start();
		//*/
		/*
		RESTController restController;
		SpringApplication.run(PetshopApplication.class, args);
		 */
	}

}
