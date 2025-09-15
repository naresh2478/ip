package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Exceptions.JackException;
import Storage.Storage;
import TaskLists.TaskList;
import Tasks.Deadline;
import Tasks.Task;
import UI.Ui;

/**
 * The AddDeadlineCommand class represents a command to add a new deadline task to the task list.
 * It takes a description and a deadline string, validates the deadline format, and adds the task
 * to the task list. The task list is then saved to the storage.
 * This class extends the Command class.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Constructs an AddDeadlineCommand with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date as a string.
     */
    public AddDeadlineCommand(String description, String deadline) {
        assert description != null && !description.trim().isEmpty() : "Description must not be empty";
        assert deadline != null && !deadline.trim().isEmpty() : "Deadline must not be empty";
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the AddDeadlineCommand. This method validates the date format of the deadline,
     * creates a new Deadline task, adds it to the task list, and saves the updated task list
     * to storage.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     * @throws JackException If the date format is invalid or the input is incorrect.
     */
    @Override

    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        assert description != null && !description.trim().isEmpty() : "Description must not be empty";
        assert deadline != null && !deadline.trim().isEmpty() : "Deadline must not be empty";

        try {
            // Validate the deadline date format
            LocalDate parsedDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Deadline(description, parsedDate.toString());
            taskList.addTask(task);
            // System.out.println("Got it. I've added this deadline:\n" + task);
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
