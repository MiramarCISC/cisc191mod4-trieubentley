package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        String createStudents = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    gpa DOUBLE NOT NULL
                )""";

        String createCourses = """
                CREATE TABLE IF NOT EXISTS courses (
                    id INT PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    student_id INT,
                    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
                )""";

        /// ADDED DELETE CASCADE TO MAKE DELETING STUDENT BY ID EASIER

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            /// DELETES EXISTING DATA WHEN RUN TO AVOID MESSY REPEATED DATA
            stmt.execute("DROP TABLE IF EXISTS courses");
            stmt.execute("DROP TABLE IF EXISTS students");
            stmt.execute(createStudents);
            stmt.execute(createCourses);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}
