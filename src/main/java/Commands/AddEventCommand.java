package Commands;

import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;
import Tasks.Task;
import Tasks.Events;

/**
 * The AddEventCommand class represents a command to add a new event task to the task list.
 * It takes a description, start time (from), and end time (to), and adds the event to the task list.
 * This class extends the Command class.
 */
public class AddEventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    // Constructor to initialize the event task details
    public AddEventCommand(String description, String from, String to) {
        assert description != null && !description.trim().isEmpty() : "Description must not be empty";
        assert from != null && !from.trim().isEmpty() : "Start time must not be empty";
        assert to != null && !to.trim().isEmpty() : "End time must not be empty";
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the AddEventCommand. This method creates a new event task using the provided
     * description, start time, and end time. The task is then added to the task list, and the
     * updated task list is saved to storage.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert description != null && !description.trim().isEmpty() : "Description must not be empty";
        assert from != null && !from.trim().isEmpty() : "Start time must not be empty";
        assert to != null && !to.trim().isEmpty() : "End time must not be empty";

        // Create a new Event task with the description and timing
        Task task = new Events(description, from, to);

        // Add the event task to the TaskList.TaskList
        taskList.addTask(task);


        // Save the updated task list to the file
        storage.saveTasks(taskList);
        return ui.showAdd(task, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
