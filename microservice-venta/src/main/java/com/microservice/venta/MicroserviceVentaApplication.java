package com.microservice.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceVentaApplication.class, args);
	}

}
