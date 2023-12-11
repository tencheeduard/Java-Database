package com.eax.petshop;

import com.eax.petshop.classes.controllers.CLIController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class PetshopApplication {

	public static void main(String[] args) {

		try {
			CLIController cli = new CLIController();

			File passwordFile = new File("src\\password.txt");
			String password = new Scanner(passwordFile).nextLine();

			cli.invoke("cdb db mysql localhost 3306 petshop root " + password);


			cli.start();
		}
		catch (Exception e)
		{

		}
		//SpringApplication.run(PetshopApplication.class, args);
	}

}
