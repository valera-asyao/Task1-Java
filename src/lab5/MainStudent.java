package lab5;

public class MainStudent {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("Иванов Иван", "ФИИТ-21", 101, new int[]{5, 5, 5, 5, 5});
        students[1] = new Student("Петров Петр", "ФИИТ-22", 102, new int[]{4, 4, 5, 4, 3});
        students[2] = new Student("Сидоров Сидор", "ПМИ-21", 103, new int[]{6, 1, 5, 5, 5}); // Некорректные оценки

        System.out.println("--- Список студентов ---");
        for (Student s : students) {
            s.printInfo();
            System.out.println("-");
        }

        System.out.println("--- Изменение оценок у второго студента ---");
        students[1].setGrades(new int[]{5, 5, 5, 5, 4});
        students[1].printInfo(); // Стипендия должна появиться
    }
}