# Banking System Simulation

A Java console-based mini banking application simulating core banking operations including savings and current accounts, deposits, withdrawals, transfers, and interest application.

## Features

- Create Savings and Current accounts
- Login with username & password authentication
- Deposit, withdraw, and transfer between accounts
- Savings: minimum balance enforcement (INR 1000), annual interest (4%)
- Current: overdraft facility up to INR 10,000
- Full timestamped transaction history per account
- Admin view of all accounts

## OOP Concepts Used

- **Inheritance** - SavingsAccount and CurrentAccount extend abstract Account
- **Abstraction** - Abstract `Account` class with `getAccountType()` template method
- **Encapsulation** - All fields private, accessed via methods
- **Polymorphism** - `withdraw()` overridden differently in each account type
- **Collections** - HashMap for account storage, ArrayList for transaction history
- **java.time.LocalDateTime** - Timestamped transaction logs

## How to Run

```bash
javac *.java
java Main
```

## Demo Credentials

| Account | Username | Password |
|---------|----------|----------|
| Savings (Arjun) | arjun | pass123 |
| Current (TechVentures) | techventures | corp456 |
| Savings (Priya) | priya | priya789 |

## File Structure

```
banking_java/
├── Account.java          - Abstract base class
├── SavingsAccount.java   - Min balance + interest logic
├── CurrentAccount.java   - Overdraft logic
├── Bank.java             - Account registry and operations
└── Main.java             - Console UI and menu
```
