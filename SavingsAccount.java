import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);
    private static Account loggedInAccount = null;

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("       " + bank.getBankName());
        System.out.println("     Banking System Simulation");
        System.out.println("============================================");
        System.out.println("Sample accounts created. Login to continue.\n");
        System.out.println("Demo credentials:");
        System.out.println("  Savings  : arjun / pass123");
        System.out.println("  Business : techventures / corp456");
        System.out.println("  Savings  : priya / priya789\n");

        while (true) {
            if (loggedInAccount == null) {
                showGuestMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private static void showGuestMenu() {
        System.out.println("1. Login");
        System.out.println("2. Open New Savings Account");
        System.out.println("3. Open New Current Account");
        System.out.println("4. Exit");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1": handleLogin(); break;
            case "2": handleOpenSavings(); break;
            case "3": handleOpenCurrent(); break;
            case "4":
                System.out.println("Thank you for banking with us. Goodbye!");
                System.exit(0);
            default: System.out.println("Invalid option.\n");
        }
    }

    private static void showUserMenu() {
        System.out.println("\n--- Logged in as: " + loggedInAccount.getHolderName() + " [" + loggedInAccount.getAccountType() + "] ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer to Another Account");
        System.out.println("5. View Transaction History");
        if (loggedInAccount instanceof SavingsAccount) {
            System.out.println("6. Apply Annual Interest");
        }
        System.out.println("7. View All Accounts (Admin View)");
        System.out.println("8. Logout");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1": loggedInAccount.printBalance(); break;
            case "2": handleDeposit(); break;
            case "3": handleWithdraw(); break;
            case "4": handleTransfer(); break;
            case "5": loggedInAccount.printTransactionHistory(); break;
            case "6":
                if (loggedInAccount instanceof SavingsAccount) {
                    ((SavingsAccount) loggedInAccount).applyInterest();
                } else System.out.println("Invalid option.");
                break;
            case "7": bank.listAllAccounts(); break;
            case "8":
                System.out.println("Logged out successfully. Goodbye, " + loggedInAccount.getHolderName() + "!");
                loggedInAccount = null;
                System.out.println();
                break;
            default: System.out.println("Invalid option.");
        }
    }

    private static void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        loggedInAccount = bank.login(username, password);
        System.out.println();
    }

    private static void handleOpenSavings() {
        System.out.print("Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Initial Deposit (min INR 1000): ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            bank.createSavingsAccount(name, username, password, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
        System.out.println();
    }

    private static void handleOpenCurrent() {
        System.out.print("Full Name / Business Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Initial Deposit: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            bank.createCurrentAccount(name, username, password, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
        System.out.println();
    }

    private static void handleDeposit() {
        System.out.print("Amount to deposit: INR ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            loggedInAccount.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }

    private static void handleWithdraw() {
        System.out.print("Amount to withdraw: INR ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            loggedInAccount.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }

    private static void handleTransfer() {
        System.out.print("Target Account Number: ");
        String targetAccNum = scanner.nextLine().trim();
        Account target = bank.findByAccountNumber(targetAccNum);
        if (target == null) { System.out.println("Account not found."); return; }
        if (target.getAccountNumber().equals(loggedInAccount.getAccountNumber())) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }
        System.out.print("Amount to transfer: INR ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            boolean success = loggedInAccount.transfer(target, amount);
            if (success) {
                System.out.println("Transfer to " + target.getHolderName() + " successful.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }
}
