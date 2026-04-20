import java.util.*;

public class QuestionBank {
    private List<Question> allQuestions = new ArrayList<>();
    private int idCounter = 1;

    public QuestionBank() {
        loadQuestions();
    }

    private void addQuestion(String text, String[] options, int correctIndex, String category) {
        allQuestions.add(new Question(idCounter++, text, options, correctIndex, category));
    }

    private void loadQuestions() {
        addQuestion("Which keyword is used to create a class in Java?",
                new String[]{"class", "Class", "new", "struct"}, 0, "Java");
        addQuestion("What does OOP stand for?",
                new String[]{"Object Oriented Programming", "Open Object Protocol", "Output Oriented Process", "Object Output Programming"}, 0, "Java");
        addQuestion("Which collection does not allow duplicate elements in Java?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"}, 2, "Java");
        addQuestion("Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2, "Java");
        addQuestion("What is the default value of a boolean in Java?",
                new String[]{"true", "false", "null", "0"}, 1, "Java");
        addQuestion("Which Python library is used for data visualization?",
                new String[]{"numpy", "pandas", "matplotlib", "requests"}, 2, "Python");
        addQuestion("What is the output of print(type([])) in Python?",
                new String[]{"<class 'tuple'>", "<class 'list'>", "<class 'dict'>", "<class 'set'>"}, 1, "Python");
        addQuestion("Which keyword is used for anonymous functions in Python?",
                new String[]{"def", "function", "lambda", "anon"}, 2, "Python");
        addQuestion("What does SQL stand for?",
                new String[]{"Simple Query Language", "Structured Query Language", "System Query Logic", "Sequential Query Language"}, 1, "Database");
        addQuestion("Which SQL clause is used to filter groups?",
                new String[]{"WHERE", "GROUP BY", "HAVING", "ORDER BY"}, 2, "Database");
        addQuestion("Which SQL join returns all rows from both tables?",
                new String[]{"INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL OUTER JOIN"}, 3, "Database");
        addQuestion("What does HTTP stand for?",
                new String[]{"HyperText Transfer Protocol", "High Transfer Text Protocol", "Hyper Transfer Text Process", "HyperText Transmission Protocol"}, 0, "Networking");
        addQuestion("Which HTTP status code means 'Not Found'?",
                new String[]{"200", "301", "403", "404"}, 3, "Networking");
        addQuestion("What is the time complexity of binary search?",
                new String[]{"O(n)", "O(n²)", "O(log n)", "O(1)"}, 2, "DSA");
        addQuestion("Which data structure uses LIFO order?",
                new String[]{"Queue", "Stack", "Linked List", "Tree"}, 1, "DSA");
        addQuestion("What does CPU stand for?",
                new String[]{"Central Processing Unit", "Computer Processing Utility", "Control Processing Unit", "Core Processing Unit"}, 0, "General CS");
        addQuestion("Which of these is not an operating system?",
                new String[]{"Windows", "Linux", "Django", "macOS"}, 2, "General CS");
        addQuestion("What does RAM stand for?",
                new String[]{"Random Access Memory", "Read Access Module", "Runtime Application Memory", "Rapid Access Mode"}, 0, "General CS");
        addQuestion("Which protocol is used to send emails?",
                new String[]{"FTP", "SMTP", "HTTP", "SSH"}, 1, "Networking");
        addQuestion("Which sorting algorithm has the best average time complexity?",
                new String[]{"Bubble Sort", "Insertion Sort", "Merge Sort", "Selection Sort"}, 2, "DSA");
    }

    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(allQuestions);
    }

    public List<Question> getRandomQuestions(int count) {
        List<Question> shuffled = new ArrayList<>(allQuestions);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, Math.min(count, shuffled.size()));
    }

    public List<Question> getByCategory(String category) {
        List<Question> result = new ArrayList<>();
        for (Question q : allQuestions) {
            if (q.getCategory().equalsIgnoreCase(category)) result.add(q);
        }
        return result;
    }

    public void listCategories() {
        Set<String> categories = new LinkedHashSet<>();
        for (Question q : allQuestions) categories.add(q.getCategory());
        System.out.println("Available categories: " + String.join(", ", categories));
    }

    public int getTotalCount() {
        return allQuestions.size();
    }
}
