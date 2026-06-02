package model;

/**
 * Base class representing a generic User.
 * Demonstrates the base class in an inheritance hierarchy.
 * 
 * This class is designed to be extended (e.g., by Player).
 */
public class User {

    protected String name;

    // Default constructor
    public User() {
        this.name = "Anonymous";
    }

    // Parameterized constructor
    public User(String name) {
        this.name = name;
    }

    // ---------- Getters & Setters ----------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Displays basic user information.
     * Can be overridden by derived classes for additional details.
     */
    public void displayInfo() {
        System.out.println("User Name: " + name);
    }

    @Override
    public String toString() {
        return "User{name='" + name + "'}";
    }
}
