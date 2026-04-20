import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("      Library Management System");
        System.out.println("=============================================");
        System.out.println("Sample data loaded. Welcome!\n");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": handleAddBook(); break;
                case "2": handleRemoveBook(); break;
                case "3": handleUpdateBook(); break;
                case "4": library.listAllBooks(); break;
                case "5": handleRegisterMember(); break;
                case "6": library.listAllMembers(); break;
                case "7": handleIssueBook(); break;
                case "8": handleReturnBook(); break;
                case "9": library.viewActiveTransactions(); break;
                case "10": library.viewAllTransactions(); break;
                case "11": handleSearchByTitle(); break;
                case "12": handleSearchByAuthor(); break;
                case "0":
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1.  Add Book          | 2.  Remove Book    | 3.  Update Book");
        System.out.println("4.  View All Books    | 5.  Register Member| 6.  View Members");
        System.out.println("7.  Issue Book        | 8.  Return Book    | 9.  Active Issues");
        System.out.println("10. All Transactions  | 11. Search by Title| 12. Search by Author");
        System.out.println("0.  Exit");
        System.out.print("Your choice: ");
    }

    private static void handleAddBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();
        library.addBook(title, author, genre);
    }

    private static void handleRemoveBook() {
        System.out.print("Enter Book ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            library.removeBook(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void handleUpdateBook() {
        System.out.print("Enter Book ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("New Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("New Author: ");
            String author = scanner.nextLine().trim();
            System.out.print("New Genre: ");
            String genre = scanner.nextLine().trim();
            library.updateBook(id, title, author, genre);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void handleRegisterMember() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        library.registerMember(name, email, phone);
    }

    private static void handleIssueBook() {
        try {
            System.out.print("Member ID: ");
            int memberId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            library.issueBook(memberId, bookId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void handleReturnBook() {
        try {
            System.out.print("Transaction ID: ");
            int txnId = Integer.parseInt(scanner.nextLine().trim());
            library.returnBook(txnId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void handleSearchByTitle() {
        System.out.print("Enter title keyword: ");
        library.searchByTitle(scanner.nextLine().trim());
    }

    private static void handleSearchByAuthor() {
        System.out.print("Enter author name: ");
        library.searchByAuthor(scanner.nextLine().trim());
    }
}
