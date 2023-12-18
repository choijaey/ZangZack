package com.kh.zangzac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 
public class ZangZackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZangZackApplication.class, args);
	}

}
