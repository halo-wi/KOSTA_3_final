package com.kosta.KOSTA_3_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kosta"})
public class Kosta3FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Kosta3FinalApplication.class, args);
	}

}
