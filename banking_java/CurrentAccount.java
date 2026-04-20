public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, String username, String password, double initialDeposit) {
        super(accountNumber, holderName, username, password, initialDeposit);
        this.overdraftLimit = 10000.0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) { System.out.println("Withdrawal amount must be positive."); return false; }
        if (amount > balance + overdraftLimit) {
            System.out.printf("Exceeds overdraft limit. Max you can withdraw: INR %.2f%n", balance + overdraftLimit);
            return false;
        }
        balance -= amount;
        logTransaction("Withdrew INR " + String.format("%.2f", amount) + " | Balance: INR " + String.format("%.2f", balance));
        System.out.printf("Withdrawn INR %.2f successfully.%n", amount);
        if (balance < 0) {
            System.out.printf("Warning: Account is in overdraft by INR %.2f%n", Math.abs(balance));
        }
        return true;
    }

    public double getOverdraftLimit() { return overdraftLimit; }

    @Override
    public String getAccountType() { return "Current Account"; }
}
