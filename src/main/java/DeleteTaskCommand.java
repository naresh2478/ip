public class DeleteTaskCommand extends Command {
    private final int taskNumber;

    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(taskNumber);
        System.out.println("Noted. I've removed this task.");
        storage.saveTasks(taskList);
    }
}