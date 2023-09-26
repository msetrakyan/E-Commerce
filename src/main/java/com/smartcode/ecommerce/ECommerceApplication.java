package com.smartcode.ecommerce;

import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.service.mail.impl.MailServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ECommerceApplication {
	public static void main(String[] args) {

		SpringApplication.run(ECommerceApplication.class, args);



	}
}