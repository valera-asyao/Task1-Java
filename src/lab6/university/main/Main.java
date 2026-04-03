package lab6.university.main;
import lab6.university.models.*;
import lab6.university.services.EnrollmentService;

public class Main {
    public static void main(String[] args) {
        Professor prof = new Professor("иванов иван");
        Course javaCourse = new Course("Java Programming", prof, 2);
        Course dbCourse = new Course("Databases", prof, 10);

        Student s1 = new Student("анна смирнова", "S001");
        Student s2 = new Student("петр петров", "S002");
        Student s3 = new Student("алексей сидоров", "S003");

        EnrollmentService.enrollStudentToCourse(s1, javaCourse);
        EnrollmentService.enrollStudentToCourse(s2, javaCourse);
        EnrollmentService.enrollStudentToCourse(s3, javaCourse);

        EnrollmentService.enrollStudentToCourse(s3, dbCourse);

        System.out.println("\n--- Статистика ---");
        System.out.println("Всего студентов создано: " + Student.totalStudents);
        System.out.println("Всего курсов создано: " + Course.totalCourses);
    }
}
