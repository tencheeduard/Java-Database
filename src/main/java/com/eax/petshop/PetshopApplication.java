package com.eax.petshop;

import com.eax.petshop.classes.UI.CLI;
import com.eax.petshop.classes.UI.REST;
import com.eax.petshop.classes.controllers.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class PetshopApplication {

	public static void main(String[] args) throws Exception {
		Controller controller = new Controller();
		CLI cli = new CLI(controller);

		File passwordFile = new File("src\\password.txt");
		String password = new Scanner(passwordFile).nextLine();

		cli.invoke("cdb db mysql localhost 3306 petshop root " + password);

		cli.start();

		if(cli.exitCode == 1)
		{
			REST rest = new REST();
			rest.setController(controller);
			SpringApplication.run(PetshopApplication.class, args);
		}
	}

}
