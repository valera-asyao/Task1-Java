package lab6.bank.models;
import lab6.bank.utils.IdGenerator;
import java.util.Date;

class Transaction {
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private Date date;

    public Transaction(String fromAccount, String toAccount, double amount) {
        this.transactionId = "TXN-" + IdGenerator.generateId();
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.date = new Date();
    }

    public static void logTransaction(Transaction transaction) {
        System.out.printf("[%s] Перевод %.2f со счета %s на счет %s (ID: %s)%n",
                transaction.date, transaction.amount, transaction.fromAccount, transaction.toAccount, transaction.transactionId);
    }
}
