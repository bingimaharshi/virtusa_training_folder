public class Question {
    private int questionId;
    private String questionText;
    private String[] options;
    private int correctOptionIndex;
    private String category;

    public Question(int questionId, String questionText, String[] options, int correctOptionIndex, String category) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.category = category;
    }

    public int getQuestionId() { return questionId; }
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public String getCorrectAnswer() { return options[correctOptionIndex]; }
    public String getCategory() { return category; }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctOptionIndex;
    }

    public void display(int questionNumber) {
        System.out.println("\nQ" + questionNumber + ". [" + category + "] " + questionText);
        char label = 'A';
        for (String option : options) {
            System.out.println("   " + label + ". " + option);
            label++;
        }
    }
}
