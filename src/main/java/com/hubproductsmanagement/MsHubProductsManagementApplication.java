package com.hubproductsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hubproductsmanagement")
public class MsHubProductsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHubProductsManagementApplication.class, args);
	}

}
