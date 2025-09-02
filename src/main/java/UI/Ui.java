package UI;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Show a welcome message to the user
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    // Read user input
    public String readCommand() {
        return scanner.nextLine();
    }

    // Show an error message
    public void showError(String message) {
        System.out.println("Error: " + message);
    }


    // Show the exit message
    public void showExitMessage() {
        System.out.println("Goodbye! Hope to see you again soon!");
    }
}
