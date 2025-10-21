package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Events;
import tasks.Task;
import ui.Ui;

/**
 * The AddEventCommand class represents a command to add a new event task to the task list.
 * It accepts a raw argument string (description + /from + start + /to + end) and performs parsing/validation in execute().
 */
public class AddEventCommand extends Command {

    private final String rawArg;

    /**
     * Constructs an AddEventCommand with the raw argument as provided by the parser.
     * Expected format: <description> /from <start> /to <end>
     *
     * @param rawArg raw argument string (may be null)
     */
    public AddEventCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the AddEventCommand. This method parses the raw argument string to extract the
     * description, start time, and end time of the event. It then creates a new event task and
     * adds it to the task list, saving the updated task list to storage.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     * @throws JackException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("The description of an event cannot be empty.");
        }
        // Expect format: <description> /from <start> /to <end>
        String[] parts = rawArg.split("/from", 2);
        if (parts.length < 2) {
            throw new JackException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String description = parts[0].trim();
        String timesPart = parts[1].trim();
        String[] timeParts = timesPart.split("/to", 2);
        if (timeParts.length < 2) {
            throw new JackException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new JackException("Invalid event format. Description and times must be provided.");
        }

        // Create a new Event task with the description and timing
        Task task = new Events(description, from, to);
        // Add the event task to the TaskList.TaskList
        taskList.addTask(task);
        // Save the updated task list to the file
        storage.saveTasks(taskList);
        return ui.showAdd(task, taskList.getTaskCount());
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
