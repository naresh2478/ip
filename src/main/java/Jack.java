import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Jack {
    private static final String FILE_PATH = "./data/Jack.txt";
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Array to store tasks (no more than 100 tasks)
        Task[] tasks = new Task[100];
        //int taskCount = 0;  // Keeps track of the number of tasks entered

        int taskCount = loadTasks(tasks);

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
                saveTasks(tasks, taskCount);

            }

            // If the user types "unmark X", unmark the task
            else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get the task index (0-based)
                tasks[taskNumber].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].getDescription());
                saveTasks(tasks, taskCount);
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
                    saveTasks(tasks, taskCount);
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
                    LocalDate deadlineDate;
                    try {
                        deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format for the deadline.");
                    }

                    tasks[taskCount] = new Deadline(description, deadlineDate.toString());
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    saveTasks(tasks, taskCount);
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
                    saveTasks(tasks, taskCount);
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
                    saveTasks(tasks, taskCount);
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

    private static int loadTasks(Task[] tasks) {
        int taskCount = 0;
        try {
            // Create file and folder if they do not exist
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            if (Files.exists(path)) {
                BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) continue; // Skip invalid lines

                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (type) {
                        case "T":
                            tasks[taskCount++] = new ToDos(description);
                            if (isDone) {
                                tasks[taskCount - 1].markAsDone();
                            }
                            break;
                        case "D":
                            if (parts.length > 3) {
                                String deadline = parts[3];
                                tasks[taskCount++] = new Deadline(description, deadline);
                                if (isDone) {
                                    tasks[taskCount - 1].markAsDone();
                                }

                            }
                            break;
                        case "E":
                            if (parts.length > 3) {
                                String fromTo = parts[3];
                                String[] timeParts = fromTo.split(" to ");
                                tasks[taskCount++] = new Events(description, timeParts[0], timeParts[1]);
                                if (isDone) {
                                    tasks[taskCount - 1].markAsDone();
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskCount;
    }

    // Method to save tasks to the file
    private static void saveTasks(Task[] tasks, int taskCount) {
        try {
            // Create file and folder if they do not exist
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks[i];
//                String taskType = task.getType();
                String taskStatus = task.isDone() ? "1" : "0";
                String taskDescription = task.getDescription();

                // Write task to file in a specific format
                if (task instanceof ToDos) {
                    bw.write("T | " + taskStatus + " | " + taskDescription + "\n");
                } else if (task instanceof Deadline) {
                    bw.write("D | " + taskStatus + " | " + taskDescription + " | " + ((Deadline) task).getDeadline() + "\n");
                } else if (task instanceof Events) {
                    bw.write("E | " + taskStatus + " | " + taskDescription + " | " + ((Events) task).getFrom() + " to " + ((Events) task).getTo() + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}




