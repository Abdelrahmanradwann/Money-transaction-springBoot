package com.example.FirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableCaching
public class TransferService {
		public static void main(String[] args) {

		ApplicationContext app = SpringApplication.run(TransferService.class, args);

	}

}


