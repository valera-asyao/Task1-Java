package lab6.university.services;

import lab6.university.models.Course;
import lab6.university.models.Student;

public class EnrollmentService {
    public static void enrollStudentToCourse(Student student, Course course) {
        if (course.getStudentCount() < course.getCapacity()) {
            course.addStudent(student);
            student.addCourse(course);
            System.out.println("Студент " + student.getFullName() + " успешно записан на курс " + course.getCourseName());
        } else {
            System.out.println("Ошибка: на курсе " + course.getCourseName() + " нет свободных мест.");
        }
    }
}
