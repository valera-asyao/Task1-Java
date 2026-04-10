package lab7.task7.main;
import static lab7.task7.models.MathUtils.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Константы: PI = " + PI + ", E = " + E);

        System.out.println("--- Проверка чисел ---");
        int num = 17;
        System.out.println(num + " простое? " + isPrime(num));

        System.out.println("--- НОД и НОК ---");
        int a = 48, b = 18;
        System.out.println("НОД(" + a + ", " + b + ") = " + gcd(a, b));
        System.out.println("НОК(" + a + ", " + b + ") = " + lcm(a, b));

        System.out.println("--- Последовательности ---");
        System.out.println("10-е число Фибоначчи: " + fibonacci(10));

        try {
            System.out.println("Факториал 20: " + factorial(20));
            // Это вызовет исключение, так как 21! > Long.MAX_VALUE
            System.out.println("Факториал 21: " + factorial(21));
        } catch (ArithmeticException e) {
            System.err.println("Ошибка вычисления факториала: " + e.getMessage());
        }

        // Попытка создать объект (вызовет ошибку компиляции, если раскомментировать)
        // MathUtils utils = new MathUtils();
    }
}
