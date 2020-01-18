package com.deaaz.deaazcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DeaazCoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DeaazCoreApplication.class, args);
	}

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder deaazCoreAplication) {
		return deaazCoreAplication.sources(DeaazCoreApplication.class);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
