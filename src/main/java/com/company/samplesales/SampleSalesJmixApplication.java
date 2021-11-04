package com.company.samplesales;

import io.jmix.core.security.CompositeUserRepository;
import io.jmix.core.security.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
public class SampleSalesJmixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSalesJmixApplication.class, args);
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix="main.datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	UserRepository userRepository() {
		return new CompositeUserRepository();
	}
}
