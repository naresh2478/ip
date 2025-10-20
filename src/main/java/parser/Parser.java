package parser;

import commands.*;
import exceptions.JackException;

public class Parser {

    // Parse user input and return the corresponding Commands.Command
    public static Command parse(String userInput) throws JackException {
        assert userInput != null : "User input should not be null";
        String[] parts = userInput.split(" ", 2);  // Split the command into the main command and its argument
        assert parts.length > 0 && parts[0] != null && !parts[0].isEmpty() : "Command cannot be empty";
        String command = parts[0].toLowerCase();

        switch (command) {

        case "list":
            return new ListCommand();

        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please provide a task description.");
            }
            return new AddTodoCommand(parts[1]);  // Pass description for Todo task

        case "deadline":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please provide a task description and a deadline.");
            }
            return parseDeadline(parts[1]);  // Pass description and deadline string for tasks.Deadline task

        case "event":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please provide a task description and a time period.");
            }
            return parseEvent(parts[1]);  // Pass description and time range for Event task

        case "mark":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please enter a valid task number.");
            }
            return new MarkTaskCommand(Integer.parseInt(parts[1]) - 1);  // Mark task by index

        case "unmark":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please enter a valid task number.");
            }
            return new UnmarkTaskCommand(Integer.parseInt(parts[1]) - 1);  // Unmark task by index

        case "delete":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please enter a valid task number.");
            }
            return new DeleteTaskCommand(Integer.parseInt(parts[1]) - 1);  // Delete task by index

        case "bye":
            return new ExitCommand();  // Exit the program

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JackException("Please provide a keyword to search for.");
            }
            return new FindCommand(parts[1]);

        case "sort":
            if (parts.length < 2 || !parts[1].trim().equalsIgnoreCase("deadlines")) {
                throw new JackException("Did you mean 'sort deadlines'? Only deadline sorting is supported.");
            }
            return new SortDeadlinesCommand();

        default:
            throw new JackException("Invalid command: " + command);  // Handle invalid command
        }
    }

    // Parse and handle the "deadline" command
    private static Command parseDeadline(String input) throws JackException {
        String[] parts = input.split("/by");
        if (parts.length < 2) {
            throw new JackException("Please provide a description and a deadline.");
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        // Return the Commands.AddDeadlineCommand with description and deadline
        return new AddDeadlineCommand(description, deadline);
    }

    // Parse and handle the "event" command
    private static Command parseEvent(String input) throws JackException {
        String[] parts = input.split("/from");
        if (parts.length < 2) {
            throw new JackException("Please provide a description and a time period.");
        }
        String description = parts[0].trim();

        // Split the second part by "/to" to get the start and end time for the event
        String[] timeParts = parts[1].split("/to");
        if (timeParts.length < 2) {
            throw new JackException("Please provide both start and end times for the event.");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        // Return the Commands.AddEventCommand with description, from and to times
        return new AddEventCommand(description, from, to);
    }
}
