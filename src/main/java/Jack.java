import java.util.Scanner;

public class Jack {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Array to store tasks (no more than 100 tasks)
        String[] tasks = new String[100];
        int taskCount = 0;  // Keeps track of the number of tasks entered

        // Greet user
        System.out.println("Hello! I'm Jack");
        System.out.println("What can I do for you?");

        while (true) {
            // Read the user input
            String userInput = scanner.nextLine();

            // If the user types "bye", exit the program
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // If the user types "list", display all stored tasks
            else if (userInput.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }

             // If the user types anything else, add it as a task
            else {
                tasks[taskCount] = userInput;  // Store the task
                taskCount++;  // Increment task count
                System.out.println("added: " + userInput);
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}
