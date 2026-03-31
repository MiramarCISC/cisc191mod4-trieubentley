package edu.sdccd.cisc191.model;

public class Course {
    private int id;
    private String title;
    private int studentId;

    public Course(int id, String title, int studentId) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be null or blank.");
        if (studentId <= 0) throw new IllegalArgumentException("Student ID must be greater than 0.");
        this.id = id;
        this.title = title;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", title='" + title + "', studentId=" + studentId + "}";
    }
}
