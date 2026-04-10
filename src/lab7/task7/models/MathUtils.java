package lab7.task7.models;

public final class MathUtils {

    public static final double PI = Math.PI;

    public static final double E = Math.E;


    private MathUtils() {
        throw new UnsupportedOperationException("Это утилитарный класс, создание объектов запрещено.");
    }


    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd(a, b);
    }


    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Число должно быть неотрицательным.");
        long result = 1;
        for (int i = 1; i <= n; i++) {
            // Проверка на переполнение перед умножением
            if (result > Long.MAX_VALUE / i) {
                throw new ArithmeticException("Переполнение: факториал " + n + " слишком велик для типа long.");
            }
            result *= i;
        }
        return result;
    }


    public static int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Индекс не может быть отрицательным.");
        if (n <= 1) return n;

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
