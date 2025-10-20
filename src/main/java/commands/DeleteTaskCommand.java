package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The DeleteTaskCommand class represents a command to delete a task from the task list.
 * It takes a task number, validates it, and removes the corresponding task from the list.
 * This class extends the Command class.
 */
public class DeleteTaskCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteTaskCommand with the given task number.
     *
     * @param taskNumber The index of the task to delete (0-based).
     */
    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteTaskCommand. This method checks if the task number is valid,
     * deletes the corresponding task from the task list, and saves the updated task list to storage.
     * If the task number is invalid, a JackException is thrown.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui The UI object used to display error messages to the user.
     * @param storage The storage object used to save the updated task list.
     * @throws JackException if the task number is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
            throw new JackException("Invalid task number. Please provide a valid task number.");
        }
        // Delete the task from the task list
        Task removedTask = taskList.getTasks().get(taskNumber); // Get the task to be removed
        taskList.deleteTask(taskNumber); // Delete the task
        // Provide feedback to the user
        System.out.println("Noted. I've removed this task:\n" + removedTask);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
        // Save the updated task list to storage
        storage.saveTasks(taskList);
        return ui.showDelete(removedTask, taskList.getTaskCount());
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
