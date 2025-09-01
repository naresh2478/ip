public class MarkTaskCommand extends Command {

    private final int taskNumber; // The task number to be marked

    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Mark the task as done in the TaskList
        taskList.markAsDone(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);
        System.out.println("Nice! I've marked this task as done:\n" + task.getStatusIcon() + " " + task.getDescription());

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
    }
}
