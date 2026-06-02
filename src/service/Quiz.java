package service;

import model.Player;
import model.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Core Quiz engine class.
 * Handles loading questions from file, running the quiz, and displaying results.
 * 
 * File Format for questions.txt (pipe-delimited):
 * questionText|option1|option2|option3|option4|correctAns
 * 
 * Example:
 * What is the capital of France?|Berlin|Madrid|Paris|Rome|C
 */
public class Quiz {

    private List<Question> questions;
    private Player player;
    private String filePath;
    private Scanner scanner;

    // Default constructor — uses default file path
    public Quiz(Scanner scanner) {
        this.questions = new ArrayList<>();
        this.filePath = "questions.txt";
        this.scanner = scanner;
    }

    // Parameterized constructor
    public Quiz(Player player, String filePath, Scanner scanner) {
        this.questions = new ArrayList<>();
        this.player = player;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    // ---------- Getters & Setters ----------

    public List<Question> getQuestions() {
        return questions;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    // ============================================================
    //  1. loadQuestions() — File Handling
    // ============================================================

    /**
     * Loads questions from the specified text file.
     * Each line in the file represents one question in pipe-delimited format:
     * 
     *   questionText|option1|option2|option3|option4|correctAnswer
     * 
     * Lines starting with '#' are treated as comments and skipped.
     * Empty lines are also skipped.
     *
     * @throws IOException if the file cannot be read
     */
    public void loadQuestions() throws IOException {
        questions.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length != 6) {
                    System.out.println("[WARNING] Skipping malformed line " + lineNumber +
                            " (expected 6 fields, found " + parts.length + ")");
                    continue;
                }

                Question question = new Question(
                        parts[0].trim(),  // questionText
                        parts[1].trim(),  // option1
                        parts[2].trim(),  // option2
                        parts[3].trim(),  // option3
                        parts[4].trim(),  // option4
                        parts[5].trim()   // correctAns (A/B/C/D)
                );

                questions.add(question);
            }
        }

        System.out.println("\n✅ Successfully loaded " + questions.size() + " question(s) from '" + filePath + "'.\n");
    }

    // ============================================================
    //  2. startQuiz() — Quiz Logic
    // ============================================================

    /**
     * Starts the interactive quiz session.
     * Iterates through all loaded questions, accepts user input,
     * validates answers, and tracks the score.
     */
    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("\n⚠️  No questions loaded! Please check the questions file.");
            return;
        }

        player.resetScore();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              🎯  QUIZ STARTED  🎯                   ║");
        System.out.println("║  Player: " + padRight(player.getName(), 42) + "║");
        System.out.println("║  Total Questions: " + padRight(String.valueOf(questions.size()), 33) + "║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.displayQuestion(i + 1);

            String userAnswer = "";
            boolean validInput = false;

            // Input validation loop
            while (!validInput) {
                System.out.print("\n   ➤ Your answer (A/B/C/D): ");
                userAnswer = scanner.nextLine().trim().toUpperCase();

                if (userAnswer.matches("[ABCD]")) {
                    validInput = true;
                } else {
                    System.out.println("   ❌ Invalid input! Please enter A, B, C, or D.");
                }
            }

            // Check answer and provide feedback
            if (q.isCorrect(userAnswer)) {
                player.incrementScore();
                System.out.println("   ✅ Correct! Well done! 🎉");
            } else {
                System.out.println("   ❌ Wrong! The correct answer was: " + q.getCorrectAns());
            }

            System.out.println("   ────────────────────────────────────────");
            System.out.println("   Current Score: " + player.getScore() + " / " + (i + 1));
        }
    }

    // ============================================================
    //  3. displayResult() — Results Display
    // ============================================================

    /**
     * Displays the final quiz results with a formatted scoreboard.
     * Includes percentage calculation and a performance grade.
     */
    public void displayResult() {
        int totalQuestions = questions.size();
        int score = player.getScore();
        double percentage = (totalQuestions > 0)
                ? ((double) score / totalQuestions) * 100
                : 0;

        String grade = getGrade(percentage);

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║               📊  QUIZ RESULTS  📊                  ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                                                      ║");
        System.out.println("║  Player      : " + padRight(player.getName(), 37) + "║");
        System.out.println("║  Score       : " + padRight(score + " / " + totalQuestions, 37) + "║");
        System.out.printf("║  Percentage  : %-37s║%n", String.format("%.1f%%", percentage));
        System.out.println("║  Grade       : " + padRight(grade, 37) + "║");
        System.out.println("║                                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");

        // Performance message
        if (percentage == 100) {
            System.out.println("║  🏆  PERFECT SCORE! Outstanding performance!         ║");
        } else if (percentage >= 80) {
            System.out.println("║  🌟  Excellent work! Keep it up!                     ║");
        } else if (percentage >= 60) {
            System.out.println("║  👍  Good effort! Room for improvement.              ║");
        } else if (percentage >= 40) {
            System.out.println("║  📚  Average. More practice needed.                  ║");
        } else {
            System.out.println("║  💪  Don't give up! Try again and improve!           ║");
        }

        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    // ---------- Helper Methods ----------

    /**
     * Returns a performance grade based on percentage.
     */
    private String getGrade(double percentage) {
        if (percentage >= 90) return "A+ (Outstanding)";
        if (percentage >= 80) return "A  (Excellent)";
        if (percentage >= 70) return "B  (Good)";
        if (percentage >= 60) return "C  (Average)";
        if (percentage >= 50) return "D  (Below Average)";
        return "F  (Needs Improvement)";
    }

    /**
     * Right-pads a string with spaces to the specified length.
     * Compatible with Java 8+ (does not use String.repeat()).
     */
    private String padRight(String text, int length) {
        if (text.length() >= length) return text;
        StringBuilder sb = new StringBuilder(text);
        for (int i = text.length(); i < length; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
