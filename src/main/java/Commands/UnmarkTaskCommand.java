package Commands;

import Exceptions.JackException;
import Storage.Storage;
import TaskLists.TaskList;
import Tasks.Task;
import UI.Ui;


/**
 * The UnmarkTaskCommand class represents a command to unmark a task as not done in the task list.
 * It takes a task number, validates it, and updates the corresponding task status to "not done".
 * This class extends the Command class.
 */
public class UnmarkTaskCommand extends Command {

    private final int taskNumber; // The task number to be unmarked

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkTaskCommand. This method validates the task number, unmarks the corresponding
     * task as not done, and saves the updated task list to storage. If the task number is invalid,
     * an exception is thrown.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The UI object used to interact with the user (not used in this method).
     * @param storage The storage object used to save the updated task list.
     * @throws IllegalArgumentException if the task number is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
            throw new JackException("Invalid task number. Please enter a valid task number.");
        }
        // Unmark the task as done in the TaskList.TaskList
        taskList.unmark(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
        return ui.showUnmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
