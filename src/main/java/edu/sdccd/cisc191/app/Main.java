package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.CourseRepository;
import edu.sdccd.cisc191.repository.JdbcCourseRepository;
import edu.sdccd.cisc191.repository.JdbcStudentRepository;
import edu.sdccd.cisc191.repository.StudentRepository;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize database (creates tables if they don't exist)
        DatabaseInitializer.initialize();

        // Create repositories and service
        /// imported StudentRepository and CourseRepository classes to use interfaces for better abstraction
        StudentRepository studentRepo = new JdbcStudentRepository();
        CourseRepository courseRepo = new JdbcCourseRepository();
        StudentService service = new StudentService(studentRepo);

        // Insert at least 3 students
        service.addStudent(new Student(1, "Alice Johnson", 3.8));
        service.addStudent(new Student(2, "Bob Martinez", 3.2));
        service.addStudent(new Student(3, "Clara Nguyen", 3.9));

        // Insert at least 3 courses linked to students
        courseRepo.save(new Course(1, "Calculus II", 1));
        courseRepo.save(new Course(2, "Physics 101", 1));
        courseRepo.save(new Course(3, "Data Structures", 2));
        courseRepo.save(new Course(4, "Java Programming", 3));

        // Print all students
        System.out.println("\n=== All Students ===");
        List<Student> allStudents = service.getAllStudents();
        for (Student s : allStudents) {
            System.out.println(s);
        }

        // Find one student by ID
        System.out.println("\n=== Find Student by ID (2) ===");
        Student found = service.getStudent(2);
        System.out.println(found);

        // Print courses for a student
        System.out.println("\n=== Courses for Student ID 1 ===");
        List<Course> coursesForStudent1 = courseRepo.findByStudentId(1);
        for (Course c : coursesForStudent1) {
            System.out.println(c);
        }

        // Update one GPA
        System.out.println("\n=== Updating GPA for Student ID 2 (3.2 → 3.6) ===");
        service.changeGpa(2, 3.6);

        // Delete student ID 3 — must delete their courses first (foreign key constraint)
        System.out.println("\n=== Deleting Student ID 3 (Clara) ===");
        /*List<Course> clarasCourses = courseRepo.findByStudentId(3);
        for (Course c : clarasCourses) {
            courseRepo.deleteById(c.getId());
        }*/
        /// made this block unnecessary after adding ON DELETE CASCADE to DatabaseInitializer

        service.removeStudent(3);
        System.out.println("Student ID 3 and their courses deleted.");

        // Print remaining students after changes
        System.out.println("\n=== Remaining Students (After Changes) ===");
        for (Student s : service.getAllStudents()) {
            System.out.println(s);
        }

        // Print remaining courses after changes
        System.out.println("\n=== Remaining Courses (After Changes) ===");
        for (Course c : courseRepo.findAll()) {
            System.out.println(c);
        }
    }
}