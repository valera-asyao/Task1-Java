package lab5;

public class MainBank {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("RU123456", "Алексей", 1000.0, 5.0);
        BankAccount acc2 = new BankAccount("KZ654321", "Мария", 500.0, 3.0);
        BankAccount acc3 = new BankAccount("AM000000", "Иван", -100.0, 0); // Проверка отрицательного баланса

        acc1.printInfo();
        acc2.printInfo();
        System.out.println("---");

        acc1.deposit(500);
        acc1.withdraw(2000); // Ошибка: недостаточно средств
        acc1.withdraw(300);

        System.out.println("\n--- Перевод ---");
        acc1.transfer(acc2, 200);

        System.out.println("\n--- Начисление процентов ---");
        acc2.applyInterest();

        System.out.println("\n--- Закрытие счета ---");
        acc1.closeAccount();
        acc1.deposit(100); // Ошибка: счет закрыт
    }
}
