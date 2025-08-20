import java.util.Scanner;

public class Jack {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

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

            // Echo the user's input
            System.out.println(userInput);
        }
        
        // Close the scanner
        scanner.close();
    }
}
