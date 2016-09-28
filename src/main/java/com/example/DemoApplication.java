package com.example;

import jdk.nashorn.api.scripting.ScriptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		log.info("Loading Database...");

//		BufferedReader in = new BufferedReader(new FileReader("import.sql"));

		jdbcTemplate.execute("DROP TABLE customers");
		jdbcTemplate.execute("CREATE TABLE customers (id INT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY (id));");
		jdbcTemplate.execute("INSERT INTO customers (first_name, last_name) VALUES ('Vanilla', 'Wilhelm');");
		jdbcTemplate.execute("INSERT INTO customers (first_name, last_name) VALUES ('Blue', 'Jeans');");

		log.info("Initial Load of Database Complete");
	}
}
