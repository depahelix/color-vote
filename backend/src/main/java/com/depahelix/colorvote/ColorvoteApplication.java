package com.depahelix.colorvote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ColorvoteApplication {

	private static final Logger log = LoggerFactory.getLogger(ColorvoteApplication.class);

	private UUID uuid = UUID.fromString("b302d375-2a77-4785-82ee-d97b60ceede2");

	public static void main(String[] args) {
		SpringApplication.run(ColorvoteApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(VoteRepository repository) {
		return (args) -> {
			VoteController.getInstance(repository);
		};
	}
//	@Bean
//	public CommandLineRunner demo(VoteRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//
//			repository.save(new Vote("cmorley", uuid));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Vote vote : repository.findAll()) {
//				log.info(vote.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(uuid)
//					.ifPresent(vote -> {
//						log.info("Customer found with findById(1L):");
//						log.info("--------------------------------");
//						log.info(vote.toString());
//						log.info("");
//					});
//
//		};
//	}








//	@Bean
//	public CommandLineRunner demo(CustomerRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1L)
//					.ifPresent(customer -> {
//						log.info("Customer found with findById(1L):");
//						log.info("--------------------------------");
//						log.info(customer.toString());
//						log.info("");
//					});
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			// 	log.info(bauer.toString());
//			// }
//			log.info("");
//		};
//	}
}
