package lab7.task2.models;
import java.time.Year;

public class Employee {
    private int id;
    private String fullName;
    private String department;
    private double salary;
    private int hireYear;

    private static int nextId;

    private Employee(int id, String fullName, String department, double salary, int hireYear ){
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        this.hireYear = hireYear;
    }

    public static Employee createEmployee(String fullName, String department, double salary, int hireYear) {
        int currentYear = Year.now().getValue();
        if (salary <= 0) {
            throw new IllegalArgumentException("Зарплата должна быть больше 0. Введено: " + salary);
        }
        if (hireYear > currentYear) {
            throw new IllegalArgumentException("Год найма не может быть в будущем. Введено: " + hireYear);
        }
        return new Employee(nextId++, fullName, department, salary, hireYear);
    }

    public static Employee createIntern(String fullName, String department){
        int currentYear = Year.now().getValue();
        return new Employee(nextId++, fullName, department, 30000, currentYear);
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getHireYear() { return hireYear; }

    // Метод для удобного вывода информации
    public void printInfo() {
        System.out.printf("ID: %d | %s | Отдел: %s | Зарплата: %.2f | Нанят в %d г.%n",
                id, fullName, department, salary, hireYear);
    }
}
