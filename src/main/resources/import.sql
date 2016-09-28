DROP TABLE customers;
CREATE TABLE customers (id INT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO customers (first_name, last_name) VALUES ('Vanilla', 'Wilhelm');
INSERT INTO customers (first_name, last_name) VALUES ('Blue', 'Jeans');