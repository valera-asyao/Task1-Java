package lab6.university.models;

import lab6.university.utils.StringUtils;
import java.util.Arrays;

public class Student {
    public static int totalStudents = 0;

    private String fullName;
    private String studentId;
    private Course[] courses;
    private int courseCount;

    public Student(String fullName, String studentId) {
        setFullName(fullName);
        setStudentId(studentId);
        this.courses = new Course[5]; // Максимум 5 курсов
        this.courseCount = 0;
        totalStudents++;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) {
        if (StringUtils.isValidName(fullName)) {
            this.fullName = StringUtils.capitalize(fullName);
        } else {
            throw new IllegalArgumentException("Некорректное имя студента.");
        }
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) {
        if (studentId != null && !studentId.isEmpty()) {
            this.studentId = studentId;
        } else {
            throw new IllegalArgumentException("ID не может быть пустым.");
        }
    }

    public void addCourse(Course course) {
        if (courseCount < courses.length) {
            courses[courseCount++] = course;
        }
    }
}