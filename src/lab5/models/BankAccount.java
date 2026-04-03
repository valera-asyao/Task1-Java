package lab5.models;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private double interestRate;
    private boolean isActive;

    // Конструктор
    public BankAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        // Проверяем начальный баланс
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Ошибка: Начальный баланс не может быть отрицательным. Установлен 0.");
            this.balance = 0;
        }
        this.interestRate = interestRate;
        this.isActive = true; // Сразу активирует счёт
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getBalance() { return balance; }
    // Сеттера для баланса нет по условию задачи

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public boolean isActive() { return isActive; }

    public void deposit(double amount) {
        if (!isActive) {
            System.out.println("Операция отклонена: счет " + accountNumber + " закрыт.");
            return;
        }
        if (amount > 0) {
            balance += amount;
            System.out.println("Счет пополнен на " + amount + ". Текущий баланс: " + balance);
        } else {
            System.out.println("Ошибка: Сумма пополнения должна быть положительной.");
        }
    }

    public void withdraw(double amount) {
        if (!isActive) {
            System.out.println("Операция отклонена: счет " + accountNumber + " закрыт.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть положительной.");
        } else if (amount > balance) {
            System.out.println("Ошибка: Недостаточно средств на счете.");
        } else {
            balance -= amount;
            System.out.println("Снято " + amount + ". Текущий баланс: " + balance);
        }
    }

    public void transfer(BankAccount to, double amount) {
        if (!this.isActive || !to.isActive()) {
            System.out.println("Ошибка: Один из счетов закрыт, перевод невозможен.");
            return;
        }
        if (amount > 0 && this.balance >= amount) {
            this.withdraw(amount);
            to.deposit(amount);
            System.out.println("Перевод " + amount + " успешно выполнен.");
        } else {
            System.out.println("Ошибка перевода: некорректная сумма или недостаточно средств.");
        }
    }

    public void applyInterest() {
        if (isActive) {
            double added = balance * interestRate / 100;
            balance += added;
            System.out.println("Начислены проценты: " + added + ". Текущий баланс: " + balance);
        }
    }

    public void closeAccount() {
        isActive = false;
        System.out.println("Счет " + accountNumber + " деактивирован.");
    }

    public void printInfo() {
        System.out.println("Счет: " + accountNumber + " | Владелец: " + ownerName +
                " | Баланс: " + balance + " | Ставка: " + interestRate + "%" +
                " | Активен: " + (isActive ? "Да" : "Нет"));
    }
}
