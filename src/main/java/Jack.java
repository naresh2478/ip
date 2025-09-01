import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Jack {
    private static final String FILE_PATH = "./data/Jack.txt";

    private final TaskList taskList;
    private final Ui ui;
    private Storage storage;

    public Jack() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(FILE_PATH);
        storage.loadTasks(taskList);
    }

    public void run() {

        ui.showWelcome();

        while (true) {
            // Read the user input through the Ui class
            String userInput = ui.readCommand();

            // If the user types "bye", exit the program
            if (userInput.equals("bye")) {
                ui.showExitMessage();  // Show exit message
                break;
            }

            // If the user types "list", display all stored tasks
            else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.getTaskCount(); i++) {
                    System.out.println((i + 1) + "." + taskList.getTasks().get(i).toString());
                }
            }

            // If the user types "mark X", mark the task as done
            else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                taskList.markAsDone(taskNumber); // Mark the task as done using TaskList
                System.out.println("Nice! I've marked this task as done:\n" + taskList.getTasks().get(taskNumber).getStatusIcon() + " " + taskList.getTasks().get(taskNumber).getDescription());
                storage.saveTasks(taskList);
            }

            // If the user types "unmark X", unmark the task
            else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                taskList.unmark(taskNumber); // Unmark the task using TaskList
                System.out.println("OK, I've marked this task as not done yet:\n" + taskList.getTasks().get(taskNumber).getStatusIcon() + " " + taskList.getTasks().get(taskNumber).getDescription());
                storage.saveTasks(taskList);
            }

            // If the user types "todo", add a ToDo task
            else if (userInput.startsWith("todo")) {
                try {
                    String description = userInput.length() > 4 ? userInput.substring(5).trim() : "";
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    Task task = new ToDos(description);
                    taskList.addTask(task);  // Add the task to TaskList
                    System.out.println("Got it. I've added this task:\n" + task);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    storage.saveTasks(taskList);
                } catch(Exception e) {
                    ui.showError(e.getMessage());
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
                    Task task = new Deadline(description, by);
                    taskList.addTask(task);  // Add the task to TaskList
                    System.out.println("Got it. I've added this task:\n" + task);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    storage.saveTasks(taskList);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
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
                    Task task = new Events(description, from, to);
                    taskList.addTask(task);  // Add the task to TaskList
                    System.out.println("Got it. I've added this task:\n" + task);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    storage.saveTasks(taskList);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            }

            else if (userInput.startsWith("delete")) {
                try {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Please provide a task number to delete.");
                    }

                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    taskList.deleteTask(taskNumber);  // Delete the task using TaskList
                    System.out.println("Noted. I've removed this task.");
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    storage.saveTasks(taskList);
                } catch (Exception e) {
                    ui.showError(e.getMessage());
                }
            }

            else {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }
    }

    public static void main(String[] args) {
        new Jack().run();
    }
}




