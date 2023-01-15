package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"configs","models","controllers","services","repositories", "com.example.main"})

public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
