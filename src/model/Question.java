package model;

/**
 * Represents a single quiz question with four options and a correct answer.
 * Demonstrates encapsulation with private fields and public getters/setters.
 */
public class Question {

    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAns;

    // Default constructor
    public Question() {
    }

    // Parameterized constructor
    public Question(String questionText, String option1, String option2,
                    String option3, String option4, String correctAns) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAns = correctAns;
    }

    // ---------- Getters & Setters ----------

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    /**
     * Displays the question along with its four options in a formatted manner.
     */
    public void displayQuestion(int questionNumber) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.printf("║  Question %d                                         ║%n", questionNumber);
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  " + questionText);
        System.out.println("╠──────────────────────────────────────────────────────╣");
        System.out.println("║  A) " + option1);
        System.out.println("║  B) " + option2);
        System.out.println("║  C) " + option3);
        System.out.println("║  D) " + option4);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    /**
     * Checks if the user's answer matches the correct answer (case-insensitive).
     *
     * @param userAnswer the answer provided by the user (A/B/C/D)
     * @return true if the answer is correct
     */
    public boolean isCorrect(String userAnswer) {
        return correctAns.trim().equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", correctAns='" + correctAns + '\'' +
                '}';
    }
}
