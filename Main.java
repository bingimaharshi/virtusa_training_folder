# Online Quiz & Assessment System

A Java console-based quiz system with timer, multiple categories, auto-scoring, and detailed performance analysis.

## Features

- 20 preloaded MCQ questions across 6 categories
- Random mode or category-specific quiz
- Configurable time limit with auto-submit on timeout
- Detailed score breakdown per category
- Visual progress bar for performance
- Performance rating message (Outstanding / Excellent / Good / Average / Keep Learning)

## OOP Concepts Used

- **Encapsulation** - All fields private with getters
- **Collections** - ArrayList, HashMap, LinkedHashMap, Set
- **Separation of Concerns** - Question, QuizSession, QuestionBank, Main
- **Java Scanner** - Console input handling
- **System.currentTimeMillis()** - Timer tracking

## How to Run

```bash
javac *.java
java Main
```

## File Structure

```
online_quiz_java/
├── Question.java       - MCQ question entity
├── QuestionBank.java   - Bank of 20 questions, category filter
├── QuizSession.java    - Timer + answer collection + result display
└── Main.java           - Menu and quiz flow
```

## Categories Available

Java, Python, Database, Networking, DSA, General CS

## Sample Output

```
Q1. [Java] Which keyword is used to create a class in Java?
   A. class   B. Class   C. new   D. struct
Your answer: A

Score           : 85.0%
Performance     : [█████████████████░░░]
Rating          : Excellent! Strong performance across categories.
```
