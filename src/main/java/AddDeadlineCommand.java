public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Deadline(description, deadline);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        storage.saveTasks(taskList);
    }
}