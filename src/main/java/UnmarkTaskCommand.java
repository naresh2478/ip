public class UnmarkTaskCommand extends Command {

    private final int taskNumber; // The task number to be unmarked

    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Unmark the task as done in the TaskList
        taskList.unmark(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:\n" + task.getStatusIcon() + " " + task.getDescription());

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
    }
}
