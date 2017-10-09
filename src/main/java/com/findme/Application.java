package com.findme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		run(args);
	}

	public static ConfigurableApplicationContext run(String[] args) {
		return SpringApplication.run(Application.class, args);
	}
}