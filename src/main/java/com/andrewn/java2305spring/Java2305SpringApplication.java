package com.andrewn.java2305spring;

import com.andrewn.java2305spring.model.Book;
import com.andrewn.java2305spring.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Java2305SpringApplication {

	private static final Logger log = LoggerFactory.getLogger(Java2305SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Java2305SpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(BookRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Book("Lord of the Rings", 1969)));
			log.info("Preloading " + repository.save(new Book("It", 1970)));
		};
	}

}
