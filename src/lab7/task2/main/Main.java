package lab7.task2.main;
import lab7.task2.models.Employee;
public class Main {
    public static void main(String[] args) {
        try {
            Employee emp1 = Employee.createEmployee("Иван Петров", "IT", 120000, 2023);
            Employee emp2 = Employee.createEmployee("Анна Сидорова", "HR", 85000, 2021);

            Employee intern1 = Employee.createIntern("Максим Новичков", "QA");
            Employee intern2 = Employee.createIntern("Ольга Ученица", "IT");

            // Вывод данных
            System.out.println("=== Список сотрудников ===");
            emp1.printInfo();
            emp2.printInfo();
            intern1.printInfo();
            intern2.printInfo();

            System.out.println("\n=== Проверка валидации ===");
            Employee badEmp = Employee.createEmployee("Джон Доу", "Маркетинг", -100, 2024);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при создании объекта: " + e.getMessage());
        }
    }

}
