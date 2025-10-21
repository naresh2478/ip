package commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

/**
 * The AddDeadlineCommand class represents a command to add a new deadline task to the task list.
 * It accepts a raw argument string (description + /by + date) and performs parsing/validation in execute().
 */
public class AddDeadlineCommand extends Command {
    private final String rawArg;

    /**
     * Constructs an AddDeadlineCommand with the raw argument as provided by the parser.
     * The raw argument should contain the description and the deadline separated by "/by".
     *
     * @param rawArg raw argument string (may be null)
     */
    public AddDeadlineCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the AddDeadlineCommand. This method parses and validates the raw argument string,
     * checks for the presence of both description and deadline date, creates a new Deadline task,
     * adds it to the task list, and saves the updated task list to storage.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     * @throws JackException If the raw argument is empty, the deadline format is invalid,
     * or the input is incorrect.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("The description of a deadline cannot be empty.");
        }
        // Expect format: <description> /by <yyyy-MM-dd>
        String[] parts = rawArg.split("/by", 2);
        if (parts.length < 2) {
            throw new JackException("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd>");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new JackException("Invalid deadline format. Description and date must be provided.");
        }

        try {
            // Validate date format
            LocalDate parsedDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Deadline(description, parsedDate.toString());
            taskList.addTask(task);
            storage.saveTasks(taskList);
            return ui.showAdd(task, taskList.getTaskCount());
        } catch (DateTimeParseException e) {
            throw new JackException("Invalid date format. Please use yyyy-MM-dd format for the deadline.");
        }
    }

    /**
     * Returns false as this command does not cause the program to exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
