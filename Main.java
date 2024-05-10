import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sharry1017";

    private TextField employeeIdField;
    private TextField nameField;
    private TextField ageField;
    private TextField emailField;
    private TextField departmentField;

    private TextArea displayArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Labels
        Label employeeIdLabel = new Label("Employee ID:");
        GridPane.setConstraints(employeeIdLabel, 0, 0);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);

        Label ageLabel = new Label("Age:");
        GridPane.setConstraints(ageLabel, 0, 2);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 3);

        Label departmentLabel = new Label("Department:");
        GridPane.setConstraints(departmentLabel, 0, 4);

        // Text Fields
        employeeIdField = new TextField();
        GridPane.setConstraints(employeeIdField, 1, 0);

        nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 1);

        ageField = new TextField();
        GridPane.setConstraints(ageField, 1, 2);

        emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 3);

        departmentField = new TextField();
        GridPane.setConstraints(departmentField, 1, 4);

        // Buttons
        Button createTableButton = new Button("Create Employee Table");
        GridPane.setConstraints(createTableButton, 0, 5);
        createTableButton.setOnAction(e -> createEmployeeTable());

        Button registerButton = new Button("Register Employee");
        GridPane.setConstraints(registerButton, 1, 5);
        registerButton.setOnAction(e -> registerEmployee());

        Button viewEmployeesButton = new Button("View Employees");
        GridPane.setConstraints(viewEmployeesButton, 0, 6);
        viewEmployeesButton.setOnAction(e -> displayEmployees());

        Button updateEmployeeButton = new Button("Update Employee");
        GridPane.setConstraints(updateEmployeeButton, 1, 6);
        updateEmployeeButton.setOnAction(e -> updateEmployee());

        // Display Area
        displayArea = new TextArea();
        displayArea.setEditable(false);
        GridPane.setConstraints(displayArea, 0, 7, 2, 1);

        grid.getChildren().addAll(
                employeeIdLabel, nameLabel, ageLabel, emailLabel, departmentLabel,
                employeeIdField, nameField, ageField, emailField, departmentField,
                createTableButton, registerButton, viewEmployeesButton, updateEmployeeButton,
                displayArea);

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createEmployeeTable() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "employee_id VARCHAR(50) UNIQUE," +
                    "name VARCHAR(50)," +
                    "age INT," +
                    "email VARCHAR(50)," +
                    "department VARCHAR(50))";
            stmt.executeUpdate(query);
            displayArea.setText("Employee table created successfully.");
        } catch (SQLException e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void registerEmployee() {
        String employeeId = employeeIdField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String department = departmentField.getText();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees (employee_id, name, age, email, department) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, employeeId);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, email);
            stmt.setString(5, department);
            stmt.executeUpdate();
            displayArea.setText("Employee registered successfully.");
        } catch (SQLException e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void displayEmployees() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("id")).append(", ")
                        .append("Employee ID: ").append(rs.getString("employee_id")).append(", ")
                        .append("Name: ").append(rs.getString("name")).append(", ")
                        .append("Age: ").append(rs.getInt("age")).append(", ")
                        .append("Email: ").append(rs.getString("email")).append(", ")
                        .append("Department: ").append(rs.getString("department")).append("\n");
            }
            displayArea.setText(result.toString());
        } catch (SQLException e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        String employeeId = employeeIdField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String department = departmentField.getText();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET name=?, age=?, email=?, department=? WHERE employee_id=?")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, email);
            stmt.setString(4, department);
            stmt.setString(5, employeeId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                displayArea.setText("Employee information updated successfully.");
            } else {
                displayArea.setText("Employee with ID " + employeeId + " not found.");
            }
        } catch (SQLException e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }
}