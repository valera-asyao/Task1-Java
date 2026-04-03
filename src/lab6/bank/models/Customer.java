package lab6.bank.models;
import lab6.bank.utils.IdGenerator;

public class Customer {
    public static int totalCustomers = 0;

    private String customerId;
    private String fullName;
    private Account[] accounts;
    private int accountCount;

    public Customer(String fullName) {
        setFullName(fullName);
        this.customerId = "CUST-" + IdGenerator.generateId();
        this.accounts = new Account[10]; // Максимум 10 счетов
        this.accountCount = 0;
        totalCustomers++;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty()) this.fullName = fullName;
        else throw new IllegalArgumentException("Имя не может быть пустым.");
    }
    public String getFullName() { return fullName; }

    public void addAccount(Account account) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = account;
        } else {
            System.out.println("Достигнут лимит счетов для клиента.");
        }
    }

    public double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < accountCount; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }
}
