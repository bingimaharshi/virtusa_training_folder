# Library Management System

A Java console application that automates library operations including book inventory, member registration, and book transactions with fine calculation.

## Features

- Add, remove, and update books
- Register library members
- Issue books with 14-day loan period
- Return books with automatic fine calculation (INR 5/day overdue)
- Search books by title or author
- View all active and past transactions

## OOP Concepts Used

- **Classes & Objects** - Book, Member, Transaction, Library
- **Encapsulation** - Private fields with getters/setters
- **Collections** - HashMap for books/members, ArrayList for transactions
- **Java Streams** - Filtering and sorting data
- **java.time.LocalDate** - Date management
- **ChronoUnit** - Overdue day calculation

## How to Run

```bash
javac *.java
java Main
```

## File Structure

```
library_management_java/
├── Book.java          - Book entity
├── Member.java        - Member entity
├── Transaction.java   - Issue/return record with fine logic
├── Library.java       - Core library operations
└── Main.java          - Console UI and menu system
```

## Sample Interaction

```
--- MENU ---
1.  Add Book  | 2.  Remove Book | 3.  Update Book
7.  Issue Book| 8.  Return Book | 9.  Active Issues
Your choice: 7
Member ID: 1
Book ID: 3
Book issued successfully. Due date: 2024-06-01
```
