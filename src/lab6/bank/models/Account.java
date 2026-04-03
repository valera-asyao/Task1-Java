package lab6.bank.models;
import lab6.bank.utils.IdGenerator;

public class Account {
    public static int totalAccounts = 0;

    private String accountNumber;
    private Customer customer;
    private double balance;
    private String currency;

    public Account(Customer customer, double initialBalance, String currency) {
        setCustomer(customer);
        setCurrency(currency);
        this.accountNumber = generateAccountNumber();
        this.balance = initialBalance >= 0 ? initialBalance : 0;
        totalAccounts++;
    }

    public static String generateAccountNumber() {
        return "ACC-" + IdGenerator.generateId();
    }

    public void setCustomer(Customer customer) {
        if (customer != null) this.customer = customer;
        else throw new IllegalArgumentException("Клиент обязателен.");
    }
    public Customer getCustomer() { return customer; }

    public void setCurrency(String currency) {
        if (currency != null && currency.length() == 3) this.currency = currency.toUpperCase();
        else throw new IllegalArgumentException("Валюта должна состоять из 3 букв (например, RUB, USD).");
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transfer(Account to, double amount) {
        if (this.withdraw(amount)) {
            to.deposit(amount);
            Transaction t = new Transaction(this.accountNumber, to.getAccountNumber(), amount);
            Transaction.logTransaction(t);
        } else {
            System.out.println("Ошибка перевода: недостаточно средств на счете " + this.accountNumber);
        }
    }
}
