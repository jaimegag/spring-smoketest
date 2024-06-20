package com.hyrulelab.spring_smoketest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.hyrulelab.spring_smoketest.config.SpringApplicationContextInitializer;

@SpringBootApplication
public class SpringSmoketestApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringSmoketestApplication.class)
			.initializers(new SpringApplicationContextInitializer())
			.listeners()
			.application()
			.run(args);
	}

}
