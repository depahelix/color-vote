package com.depahelix.colorvote;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ColorvoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColorvoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(VoteRepository repository) {
		return (args) -> {
			VoteController.getInstance(repository);
		};
	}

}
