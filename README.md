import java.util.*;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private int accountCounter = 1001;
    private static final String BANK_NAME = "SecureBank India";

    public Bank() {
        createSavingsAccount("Arjun Mehta", "arjun", "pass123", 15000);
        createCurrentAccount("TechVentures Pvt Ltd", "techventures", "corp456", 50000);
        createSavingsAccount("Priya Sharma", "priya", "priya789", 8000);
    }

    public SavingsAccount createSavingsAccount(String name, String username, String password, double initialDeposit) {
        if (initialDeposit < 1000) {
            System.out.println("Minimum initial deposit for Savings Account is INR 1000.");
            return null;
        }
        String accNum = "SAV" + (accountCounter++);
        SavingsAccount account = new SavingsAccount(accNum, name, username, password, initialDeposit);
        accounts.put(accNum, account);
        System.out.println("Savings Account created. Account Number: " + accNum);
        return account;
    }

    public CurrentAccount createCurrentAccount(String name, String username, String password, double initialDeposit) {
        String accNum = "CUR" + (accountCounter++);
        CurrentAccount account = new CurrentAccount(accNum, name, username, password, initialDeposit);
        accounts.put(accNum, account);
        System.out.println("Current Account created. Account Number: " + accNum);
        return account;
    }

    public Account login(String username, String password) {
        for (Account account : accounts.values()) {
            if (account.authenticate(username, password)) {
                System.out.println("Login successful. Welcome, " + account.getHolderName() + "!");
                return account;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
        return null;
    }

    public Account findByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void listAllAccounts() {
        System.out.println("\n=== " + BANK_NAME + " - All Accounts ===");
        if (accounts.isEmpty()) {
            System.out.println("No accounts registered.");
            return;
        }
        System.out.printf("%-12s %-25s %-20s %15s%n", "Acc Number", "Holder Name", "Type", "Balance (INR)");
        System.out.println("-".repeat(75));
        accounts.values().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .forEach(acc -> System.out.printf("%-12s %-25s %-20s %15.2f%n",
                        acc.getAccountNumber(), acc.getHolderName(),
                        acc.getAccountType(), acc.getBalance()));
    }

    public String getBankName() { return BANK_NAME; }
}
