package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The MarkTaskCommand class represents a command to mark a task as done in the task list.
 * It takes a task number, validates it, and updates the corresponding task status to "done".
 * This class extends the Command class.
 */
public class MarkTaskCommand extends Command {

    private final int taskNumber; // The task number to be marked

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkTaskCommand. This method validates the task number, marks the corresponding
     * task as done, and saves the updated task list to storage. If the task number is invalid,
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
        // Mark the task as done in the TaskList.TaskList
        taskList.markAsDone(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);
        System.out.println("Nice! I've marked this task as done:\n" + task.getStatusIcon() + " " + task.getDescription());

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
        return ui.showMark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
