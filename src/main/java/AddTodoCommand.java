public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new ToDos(description);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        storage.saveTasks(taskList);
    }
}
