package lab5.models;

import java.util.Arrays;

public class Student {
    private String fullName;
    private String groupId;
    private int studentId;
    private int[] grades;
    private boolean hasScholarship;

    // Конструктор
    public Student(String fullName, String groupId, int studentId, int[] grades) {
        this.fullName = fullName;
        this.groupId = groupId;
        this.studentId = studentId;
        setGrades(grades); // Устанавливаем оценки с проверкой и сразу обновляем статус стипендии
    }


    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int[] getGrades() { return grades; }

    public void setGrades(int[] grades) {
        if (grades != null && grades.length == 5) {
            this.grades = new int[5];
            for (int i = 0; i < 5; i++) {
                if (grades[i] >= 2 && grades[i] <= 5) {
                    this.grades[i] = grades[i];
                } else {
                    System.out.println("Ошибка: Оценка должна быть от 2 до 5. Установлена 2.");
                    this.grades[i] = 2;
                }
            }
        } else {
            System.out.println("Ошибка: Массив должен содержать ровно 5 оценок.");
            this.grades = new int[]{2, 2, 2, 2, 2};
        }
        updateScholarshipStatus(); // Пересчитываем стипендию при изменении оценок
    }

    public boolean isHasScholarship() { return hasScholarship; }

    public double calculateAverage() {
        if (grades == null) return 0.0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public void updateScholarshipStatus() {
        this.hasScholarship = calculateAverage() >= 4.5;
    }

    public boolean isExcellent() {
        for (int grade : grades) {
            if (grade != 5) return false;
        }
        return true;
    }

    public void printInfo() {
        System.out.println("Студент: " + fullName + " (Группа: " + groupId + ", Билет: " + studentId + ")");
        System.out.println("Оценки: " + Arrays.toString(grades));
        System.out.println("Средний балл: " + calculateAverage());
        System.out.println("Стипендия: " + (hasScholarship ? "Да" : "Нет"));
        System.out.println("Отличник: " + (isExcellent() ? "Да" : "Нет"));
    }
}