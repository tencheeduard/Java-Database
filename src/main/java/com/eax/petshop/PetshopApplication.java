package com.eax.petshop;

import com.eax.petshop.classes.UI.CLI;
import com.eax.petshop.classes.controllers.Controller;
import com.eax.petshop.classes.controllers.RESTController;
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

		RESTController restController;
		SpringApplication.run(PetshopApplication.class, args);
	}

}
