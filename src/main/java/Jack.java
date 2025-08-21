import java.util.Scanner;

public class Jack {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Array to store tasks (no more than 100 tasks)
        Task[] tasks = new Task[100];
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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
            }

            // If the user types "mark X", mark the task as done
            else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get the task index (0-based)
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].getDescription());
                
            }

            // If the user types "unmark X", unmark the task
            else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get the task index (0-based)
                tasks[taskNumber].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].getDescription());
            }

            // If the user types "todo", add a ToDo task
            else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);  // Remove the "todo " part
                tasks[taskCount] = new ToDos(description);
                taskCount++;
                System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }

            // If the user types "deadline", add a Deadline task
            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by");
                String description = parts[0].substring(9).trim();
                String by = parts[1].trim();
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }

            // If the user types "event", add an Event task
            else if (userInput.startsWith("event")) {
                String[] parts = userInput.split("/from");
                String description = parts[0].substring(6).trim();
                String[] timeParts = parts[1].split("/to");
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                tasks[taskCount] = new Events(description, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }

             // If the user types anything else, add it as a task
            // else {
            //     tasks[taskCount] = new Task(userInput);  // Store the task
            //     taskCount++;  // Increment task count
            //     System.out.println("added: " + userInput);
            // }
        }
        
        // Close the scanner
        scanner.close();
    }
}



