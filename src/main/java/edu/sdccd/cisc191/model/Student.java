package edu.sdccd.cisc191.model;

public class Student {
    private final int id; /// made int final
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0.");
        /// changed from .isBlank() to .trim().isEmpty() to improve validation
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name must not be null or blank.");
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name must not be null or blank.");
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}
