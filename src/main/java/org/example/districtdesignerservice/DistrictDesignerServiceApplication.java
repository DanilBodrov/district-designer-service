package org.example.districtdesignerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistrictDesignerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistrictDesignerServiceApplication.class, args);
		System.out.println("http://localhost:8080/index");
	}

}
