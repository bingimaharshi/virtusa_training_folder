import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int bookIdCounter = 1;
    private int memberIdCounter = 1;
    private int transactionIdCounter = 1;

    public Library() {
        seedSampleData();
    }

    private void seedSampleData() {
        addBook("The Alchemist", "Paulo Coelho", "Fiction");
        addBook("Clean Code", "Robert C. Martin", "Technology");
        addBook("Atomic Habits", "James Clear", "Self-Help");
        addBook("The Great Gatsby", "F. Scott Fitzgerald", "Classic");
        addBook("Deep Work", "Cal Newport", "Productivity");
        addBook("1984", "George Orwell", "Dystopian");

        registerMember("Aarav Mehta", "aarav@email.com", "9876543210");
        registerMember("Priya Sharma", "priya@email.com", "9123456789");
        registerMember("Rishi Nair", "rishi@email.com", "9988776655");
    }

    public void addBook(String title, String author, String genre) {
        Book book = new Book(bookIdCounter++, title, author, genre);
        books.put(book.getBookId(), book);
        System.out.println("Book added: " + book.getTitle() + " (ID: " + book.getBookId() + ")");
    }

    public void removeBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) { System.out.println("Book not found."); return; }
        boolean isIssued = transactions.stream()
                .anyMatch(t -> t.getBookId() == bookId && !t.isReturned());
        if (isIssued) { System.out.println("Cannot remove: book is currently issued."); return; }
        books.remove(bookId);
        System.out.println("Book removed: " + book.getTitle());
    }

    public void updateBook(int bookId, String title, String author, String genre) {
        Book book = books.get(bookId);
        if (book == null) { System.out.println("Book not found."); return; }
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        System.out.println("Book updated successfully.");
    }

    public void registerMember(String name, String email, String phone) {
        Member member = new Member(memberIdCounter++, name, email, phone);
        members.put(member.getMemberId(), member);
        System.out.println("Member registered: " + member.getName() + " (ID: " + member.getMemberId() + ")");
    }

    public void issueBook(int memberId, int bookId) {
        Member member = members.get(memberId);
        Book book = books.get(bookId);
        if (member == null) { System.out.println("Member not found."); return; }
        if (book == null) { System.out.println("Book not found."); return; }
        if (!book.isAvailable()) { System.out.println("Sorry, this book is already issued."); return; }

        book.setAvailable(false);
        Transaction txn = new Transaction(transactionIdCounter++, memberId, bookId);
        transactions.add(txn);
        System.out.println("Book issued successfully. Due date: " + txn.getDueDate());
    }

    public void returnBook(int transactionId) {
        Transaction txn = transactions.stream()
                .filter(t -> t.getTransactionId() == transactionId && !t.isReturned())
                .findFirst().orElse(null);
        if (txn == null) { System.out.println("Active transaction not found."); return; }

        txn.returnBook();
        books.get(txn.getBookId()).setAvailable(true);
        double fine = txn.calculateFine();
        System.out.println("Book returned successfully.");
        if (fine > 0) {
            System.out.println("Late return fine: INR " + String.format("%.2f", fine));
        } else {
            System.out.println("No fine. Returned on time.");
        }
    }

    public void searchByTitle(String keyword) {
        List<Book> results = books.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        printSearchResults(results, "title", keyword);
    }

    public void searchByAuthor(String keyword) {
        List<Book> results = books.values().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        printSearchResults(results, "author", keyword);
    }

    private void printSearchResults(List<Book> results, String field, String keyword) {
        if (results.isEmpty()) {
            System.out.println("No books found matching " + field + ": " + keyword);
        } else {
            System.out.println("Search results (" + results.size() + " found):");
            results.forEach(System.out::println);
        }
    }

    public void listAllBooks() {
        if (books.isEmpty()) { System.out.println("No books in library."); return; }
        System.out.println("\n--- All Books ---");
        books.values().stream()
                .sorted(Comparator.comparingInt(Book::getBookId))
                .forEach(System.out::println);
    }

    public void listAllMembers() {
        if (members.isEmpty()) { System.out.println("No members registered."); return; }
        System.out.println("\n--- All Members ---");
        members.values().stream()
                .sorted(Comparator.comparingInt(Member::getMemberId))
                .forEach(System.out::println);
    }

    public void viewAllTransactions() {
        if (transactions.isEmpty()) { System.out.println("No transactions yet."); return; }
        System.out.println("\n--- All Transactions ---");
        transactions.forEach(System.out::println);
    }

    public void viewActiveTransactions() {
        List<Transaction> active = transactions.stream()
                .filter(t -> !t.isReturned()).collect(Collectors.toList());
        if (active.isEmpty()) { System.out.println("No active transactions."); return; }
        System.out.println("\n--- Active Issued Books ---");
        active.forEach(System.out::println);
    }
}
