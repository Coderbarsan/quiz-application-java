package model;

/**
 * Derived class from User — represents a quiz player.
 * Demonstrates inheritance: Player IS-A User with an additional 'score' attribute.
 */
public class Player extends User {

    private int score;

    // Default constructor
    public Player() {
        super();
        this.score = 0;
    }

    // Parameterized constructor
    public Player(String name) {
        super(name);
        this.score = 0;
    }

    // ---------- Getters & Setters ----------

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increments the player's score by 1.
     */
    public void incrementScore() {
        this.score++;
    }

    /**
     * Resets the player's score back to zero.
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Overrides the base class method to display player-specific info
     * including the score. Demonstrates polymorphism.
     */
    @Override
    public void displayInfo() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         PLAYER INFORMATION           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  Name  : " + name);
        System.out.println("║  Score : " + score);
        System.out.println("╚══════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', score=" + score + "}";
    }
}
