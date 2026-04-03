package lab6.university.models;
import java.util.Arrays;

public class Course {
    public static int totalCourses = 0;

    private String courseName;
    private Professor teacher;
    private Student[] students;
    private int studentCount;

    public Course(String courseName, Professor teacher, int capacity) {
        setCourseName(courseName);
        setTeacher(teacher);
        this.students = new Student[capacity];
        this.studentCount = 0;
        totalCourses++;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) {
        if (courseName != null && !courseName.trim().isEmpty()) {
            this.courseName = courseName;
        } else {
            throw new IllegalArgumentException("Название курса не может быть пустым.");
        }
    }

    public Professor getTeacher() { return teacher; }
    public void setTeacher(Professor teacher) {
        if (teacher != null) this.teacher = teacher;
        else throw new IllegalArgumentException("У курса должен быть профессор.");
    }

    public boolean addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
            return true;
        }
        return false;
    }

    public int getCapacity() { return students.length; }
    public int getStudentCount() { return studentCount; }
}