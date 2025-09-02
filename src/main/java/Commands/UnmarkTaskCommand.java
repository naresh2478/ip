package Commands;

import Storage.Storage;
import Tasks.Task;
import TaskLists.TaskList;
import UI.Ui;

public class UnmarkTaskCommand extends Command {

    private final int taskNumber; // The task number to be unmarked

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
            throw new IllegalArgumentException("Invalid task number. Please enter a valid task number.");
        }
        // Unmark the task as done in the TaskList.TaskList
        taskList.unmark(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:\n" + task.getStatusIcon() + " " + task.getDescription());

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
    }
}
