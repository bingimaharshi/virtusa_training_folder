import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected String username;
    protected String password;
    protected List<String> transactionHistory;
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Account(String accountNumber, String holderName, String username, String password, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.username = username;
        this.password = password;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account created with initial deposit of INR " + String.format("%.2f", initialDeposit));
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean deposit(double amount) {
        if (amount <= 0) { System.out.println("Deposit amount must be positive."); return false; }
        balance += amount;
        logTransaction("Deposited INR " + String.format("%.2f", amount) + " | Balance: INR " + String.format("%.2f", balance));
        System.out.println("Deposited INR " + String.format("%.2f", amount) + " successfully.");
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) { System.out.println("Withdrawal amount must be positive."); return false; }
        if (amount > balance) { System.out.println("Insufficient balance. Available: INR " + String.format("%.2f", balance)); return false; }
        balance -= amount;
        logTransaction("Withdrew INR " + String.format("%.2f", amount) + " | Balance: INR " + String.format("%.2f", balance));
        System.out.println("Withdrawn INR " + String.format("%.2f", amount) + " successfully.");
        return true;
    }

    public boolean transfer(Account target, double amount) {
        if (this.withdraw(amount)) {
            target.deposit(amount);
            logTransaction("Transferred INR " + String.format("%.2f", amount) + " to Account: " + target.getAccountNumber());
            target.logTransaction("Received INR " + String.format("%.2f", amount) + " from Account: " + this.accountNumber);
            return true;
        }
        return false;
    }

    protected void logTransaction(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        transactionHistory.add("[" + timestamp + "] " + message);
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History for " + holderName + " ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            transactionHistory.forEach(System.out::println);
        }
    }

    public void printBalance() {
        System.out.println("Account Holder : " + holderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Type   : " + getAccountType());
        System.out.println("Balance        : INR " + String.format("%.2f", balance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
    public abstract String getAccountType();
}
