package UI;

import Tasks.Task;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    public String showError(String message) {
        return message;
    }

    public String showAdd(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String showDelete(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public String showList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        IntStream.rangeClosed(1, tasks.size())
                .forEach(i -> sb.append(i).append(". ").append(tasks.get(i - 1)).append("\n"));

        return sb.toString();
    }

    public String showFind(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No tasks found matching the keyword.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        IntStream.rangeClosed(1, foundTasks.size())
                .forEach(i -> sb.append(i).append(". ").append(foundTasks.get(i - 1)).append("\n"));

        return sb.toString();
    }


    // Show the exit message
    public String showExitMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

}

