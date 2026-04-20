import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private int transactionId;
    private int memberId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    private static final int LOAN_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;

    public Transaction(int transactionId, int memberId, int bookId) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(LOAN_DAYS);
        this.isReturned = false;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.isReturned = true;
    }

    public double calculateFine() {
        if (!isReturned) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0;
        }
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysOverdue > 0 ? daysOverdue * FINE_PER_DAY : 0;
    }

    public int getTransactionId() { return transactionId; }
    public int getMemberId() { return memberId; }
    public int getBookId() { return bookId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return isReturned; }

    @Override
    public String toString() {
        String status = isReturned
                ? "Returned on " + returnDate + " | Fine: INR " + String.format("%.2f", calculateFine())
                : "Due by " + dueDate + " | Fine so far: INR " + String.format("%.2f", calculateFine());
        return String.format("[Txn #%d] Member: %d | Book: %d | Issued: %s | %s",
                transactionId, memberId, bookId, issueDate, status);
    }
}
