package com.kosta.KOSTA_3_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
@SpringBootApplication
@ComponentScan(basePackages = {"com.kosta"})
public class Kosta3FinalApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Kosta3FinalApplication.class, args);
	
			}
	}

