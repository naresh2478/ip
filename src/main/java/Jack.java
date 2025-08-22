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
                try {
                    String description = userInput.length() > 4 ? userInput.substring(5).trim() : "";  // Remove the "todo " part
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new ToDos(description);
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }   
            }

            // If the user types "deadline", add a Deadline task
            else if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.split("/by");
                    if (parts.length < 2 || parts[0].substring(9).trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Please provide a description and a deadline using '/by'.");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            }  

            // If the user types "event", add an Event task
            else if (userInput.startsWith("event")) {
                try {
                    String[] parts = userInput.split("/from");
                    if (parts.length < 2 || parts[0].substring(6).trim().isEmpty()) {
                        throw new IllegalArgumentException("Please provide a description and start time using '/from'.");
                    }
                    String description = parts[0].substring(6).trim();
                    String[] timeParts = parts[1].split("/to");
                    String from = timeParts[0].trim();
                    String to = timeParts[1].trim();
                    tasks[taskCount] = new Events(description, from, to);
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            }

            else if (userInput.startsWith("delete")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) { // No number provided
                        throw new IllegalArgumentException("Please provide a task number to delete.");
                    }

                    int taskNumber = Integer.parseInt(parts[1]) - 1;

                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new IllegalArgumentException("Invalid task number.");
                    }
                    Task removedTask = tasks[taskNumber];
                    // Shift all tasks after the deleted one left by one
                    for (int i = taskNumber; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    tasks[taskCount - 1] = null; // Clear last element
                    taskCount--;
                    System.out.println("Noted. I've removed this task:\n  " + removedTask);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("OOPS!!! " + e.getMessage());
                }
            }

            else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
        
        // Close the scanner
        scanner.close();
    }
}
