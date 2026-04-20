public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04;
    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(String accountNumber, String holderName, String username, String password, double initialDeposit) {
        super(accountNumber, holderName, username, password, initialDeposit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            System.out.printf("Cannot withdraw. Minimum balance of INR %.2f must be maintained.%n", MIN_BALANCE);
            return false;
        }
        return super.withdraw(amount);
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        logTransaction(String.format("Interest applied at %.0f%% annual: INR %.2f | New Balance: INR %.2f",
                INTEREST_RATE * 100, interest, balance));
        System.out.printf("Interest of INR %.2f applied. New balance: INR %.2f%n", interest, balance);
    }

    @Override
    public String getAccountType() { return "Savings Account"; }
}
