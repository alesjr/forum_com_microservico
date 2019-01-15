package com.forum.upvotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.forum.upvotes.Entity"} )
@EnableJpaRepositories(basePackages = {"com.forum.upvotes.Repository"})
public class UpvotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpvotesApplication.class, args);
	}

}

