package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
public class CustomerController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private DemoApplication demo = new DemoApplication();

    @RequestMapping("/customer")
    public ResponseEntity<Customer> customer(@RequestParam(value="name", defaultValue="Vanilla") String name) {
        List customersList = jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = '" + name + "';",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
        ResponseEntity<Customer> response;

        try  {
            Customer customer = (Customer) customersList.iterator().next();

            response = new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(new Customer(), HttpStatus.NOT_FOUND);;
        }
        return response;
    }
}