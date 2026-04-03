package lab6.bank.main;
import lab6.bank.models.*;

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer("Алексей Смирнов");
        Customer c2 = new Customer("Елена Иванова");

        Account acc1 = new Account(c1, 10000, "RUB");
        Account acc2 = new Account(c2, 5000, "RUB");

        c1.addAccount(acc1);
        c2.addAccount(acc2);

        System.out.println("Баланс Алексея до перевода: " + acc1.getBalance());
        System.out.println("Баланс Елены до перевода: " + acc2.getBalance());

        // Выполняем перевод
        acc1.transfer(acc2, 3500);

        System.out.println("Баланс Алексея после перевода: " + acc1.getBalance());
        System.out.println("Общий баланс Елены: " + c2.getTotalBalance());

        System.out.println("\nСтатистика:");
        System.out.println("Всего клиентов: " + Customer.totalCustomers);
        System.out.println("Всего счетов: " + Account.totalAccounts);
    }
}
