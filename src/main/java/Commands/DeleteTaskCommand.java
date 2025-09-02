package Commands;

import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;
import Tasks.Task;



public class DeleteTaskCommand extends Command {
    private final int taskNumber;

    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Error handling for invalid task number
        try {
            if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
                throw new IllegalArgumentException("Invalid task number. Please provide a valid task number.");
            }

            // Delete the task from the task list
            Task removedTask = taskList.getTasks().get(taskNumber);  // Get the task to be removed
            taskList.deleteTask(taskNumber);  // Delete the task

            // Provide feedback to the user
            System.out.println("Noted. I've removed this task:\n" + removedTask);
            System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");

            // Save the updated task list to storage
            storage.saveTasks(taskList);

        } catch (Exception e) {
            // Handle invalid task number error
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
