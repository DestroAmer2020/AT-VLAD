CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    isEmployed BOOLEAN
);

CREATE TABLE Address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50),
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);