package com.smartcode.ecommerce;

import com.smartcode.ecommerce.service.UserService;
import com.smartcode.ecommerce.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ECommerceApplication {
	public static void main(String[] args) {


		SpringApplication.run(ECommerceApplication.class, args);

	}
}