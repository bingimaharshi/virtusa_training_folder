import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static QuestionBank questionBank = new QuestionBank();

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      Online Quiz & Assessment System");
        System.out.println("==========================================");

        while (true) {
            System.out.println("\n1. Start Quiz");
            System.out.println("2. View All Questions (Admin)");
            System.out.println("3. List Categories");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": startQuizFlow(); break;
                case "2": viewAllQuestions(); break;
                case "3": questionBank.listCategories(); break;
                case "4":
                    System.out.println("Thanks for using the Quiz System. Keep learning!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void startQuizFlow() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";

        System.out.println("\nQuiz Mode:");
        System.out.println("1. Random Questions");
        System.out.println("2. Category-specific");
        System.out.print("Choose mode: ");
        String mode = scanner.nextLine().trim();

        List<Question> selectedQuestions;

        if (mode.equals("2")) {
            questionBank.listCategories();
            System.out.print("Enter category name: ");
            String category = scanner.nextLine().trim();
            selectedQuestions = questionBank.getByCategory(category);
            if (selectedQuestions.isEmpty()) {
                System.out.println("No questions found for that category. Starting random quiz.");
                selectedQuestions = questionBank.getRandomQuestions(10);
            } else {
                System.out.println("Found " + selectedQuestions.size() + " questions in this category.");
            }
        } else {
            System.out.print("How many questions? (1-" + questionBank.getTotalCount() + "): ");
            int count = 10;
            try {
                count = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Defaulting to 10 questions.");
            }
            selectedQuestions = questionBank.getRandomQuestions(count);
        }

        System.out.print("Time limit in seconds (e.g. 120): ");
        int timeLimit = 120;
        try {
            timeLimit = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Defaulting to 120 seconds.");
        }

        System.out.println("\nPress Enter to begin the quiz...");
        scanner.nextLine();

        QuizSession session = new QuizSession(name, selectedQuestions, timeLimit);
        session.start(scanner);
        session.showResults();
    }

    private static void viewAllQuestions() {
        System.out.println("\n--- All Questions in Bank ---");
        List<Question> all = questionBank.getAllQuestions();
        for (int i = 0; i < all.size(); i++) {
            Question q = all.get(i);
            System.out.printf("%2d. [%s] %s%n", (i + 1), q.getCategory(), q.getQuestionText());
            System.out.println("    Correct Answer: " + q.getCorrectAnswer());
        }
        System.out.println("\nTotal: " + all.size() + " questions");
    }
}
