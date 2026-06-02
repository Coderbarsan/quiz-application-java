import model.Player;
import service.Quiz;

import java.io.IOException;
import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║                    QUIZ APPLICATION                          ║
 * ║                                                              ║
 * ║  System Design:                                              ║
 * ║   1. Class Question  — question model with 4 options         ║
 * ║   2. Class User      — base class (name)                     ║
 * ║   3. Class Player    — derived from User (score)             ║
 * ║   4. Class Quiz      — loadQuestions, startQuiz, displayResult║
 * ║   5. File Handling   — reads from questions.txt              ║
 * ║                                                              ║
 * ║  OOP Concepts Demonstrated:                                  ║
 * ║   • Encapsulation  (private fields, getters/setters)         ║
 * ║   • Inheritance     (Player extends User)                    ║
 * ║   • Polymorphism    (displayInfo() overridden in Player)     ║
 * ║   • Abstraction     (Quiz hides internal logic)              ║
 * ║   • File I/O        (BufferedReader to read questions.txt)   ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * @author SDE Quiz Application
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ── Welcome Banner ──
        printBanner();

        // ── Get Player Name ──
        System.out.print("   Enter your name: ");
        String playerName = scanner.nextLine().trim();

        if (playerName.isEmpty()) {
            playerName = "Player";
        }

        // Create Player object (Derived class — inherits from User)
        Player player = new Player(playerName);

        System.out.println("\n   Welcome, " + player.getName() + "! Let's test your knowledge.\n");

        // ── Quiz Loop ──
        boolean playAgain = true;

        while (playAgain) {

            // Create Quiz and set the player
            Quiz quiz = new Quiz(player, "questions.txt", scanner);

            try {
                // Step 1: Load questions from file
                quiz.loadQuestions();

                if (quiz.getTotalQuestions() == 0) {
                    System.out.println("   ⚠️  No questions found in the file. Exiting.");
                    break;
                }

                // Step 2: Start the quiz
                quiz.startQuiz();

                // Step 3: Display results
                quiz.displayResult();

                // Display player info (demonstrates polymorphism)
                System.out.println();
                player.displayInfo();

            } catch (IOException e) {
                System.out.println("\n   ❌ Error reading questions file: " + e.getMessage());
                System.out.println("   Please ensure 'questions.txt' exists in the project directory.");
                System.out.println("   File format: questionText|option1|option2|option3|option4|correctAns");
                break;
            }

            // Ask to play again
            System.out.print("\n   ➤ Play again? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();
            playAgain = choice.equals("Y") || choice.equals("YES");

            if (playAgain) {
                player.resetScore();
                System.out.println("\n   🔄 Restarting quiz...\n");
            }
        }

        // ── Goodbye ──
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║       👋  Thanks for playing! See you next time!     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        scanner.close();
    }

    /**
     * Prints the welcome banner for the application.
     */
    private static void printBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║           ██████╗ ██╗   ██╗██╗███████╗               ║");
        System.out.println("║          ██╔═══██╗██║   ██║██║╚══███╔╝               ║");
        System.out.println("║          ██║   ██║██║   ██║██║  ███╔╝                ║");
        System.out.println("║          ██║▄▄ ██║██║   ██║██║ ███╔╝                 ║");
        System.out.println("║          ╚██████╔╝╚██████╔╝██║███████╗               ║");
        System.out.println("║           ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝              ║");
        System.out.println("║                                                      ║");
        System.out.println("║            📝  QUIZ APPLICATION v1.0  📝             ║");
        System.out.println("║                                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║  Test your knowledge with our interactive quiz!      ║");
        System.out.println("║  Answer A, B, C, or D for each question.            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }
}
