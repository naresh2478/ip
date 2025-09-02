public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }

        Task task = new ToDos(description);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        storage.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
