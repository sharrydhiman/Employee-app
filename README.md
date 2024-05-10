# Employee Management System

This Java application is designed to manage employee information using a MySQL database.

## Features

- Create an employee table in the database.
- Register new employees with their details such as ID, name, age, email, and department.
- View all registered employees.
- Update employee information.

## Requirements

- Java Development Kit (JDK)
- MySQL Database Server
- MySQL Connector/J library

## Setup Instructions

1. **Install JDK:**
   Make sure you have Java Development Kit (JDK) installed on your system. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Install MySQL:**
   Install MySQL Database Server on your system. You can download MySQL from the [official MySQL website](https://dev.mysql.com/downloads/).

3. **Download MySQL Connector/J:**
   Download the MySQL Connector/J JAR file from the [MySQL Connector/J download page](https://dev.mysql.com/downloads/connector/j/).

4. **Set Up Database:**
   - Create a new database in MySQL. For example, `employee_db`.
   - Run the `createEmployeeTable()` method in the application to create the necessary table structure in your database.

5. **Configure JDBC Connection:**
   - Open the Java application (`Main.java`) in your preferred IDE.
   - Update the JDBC connection parameters in the `JDBC_URL`, `JDBC_USER`, and `JDBC_PASSWORD` variables according to your MySQL database configuration.

6. **Run the Application:**
   - Compile and run the Java application.
   - Use the GUI to perform operations such as registering employees, viewing employees, and updating employee information.

## Usage

- Launch the application and interact with the graphical user interface (GUI) to perform CRUD (Create, Read, Update, Delete) operations on employee data.

## Troubleshooting

- If you encounter any issues related to database connectivity or SQL queries, check the console output for error messages. Ensure that your MySQL server is running and the database connection parameters are correctly configured in the application.

---
