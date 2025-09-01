public class AddEventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    // Constructor to initialize the event task details
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Create a new Event task with the description and timing
        Task task = new Events(description, from, to);

        // Add the event task to the TaskList
        taskList.addTask(task);

        // Show success message
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");

        // Save the updated task list to the file
        storage.saveTasks(taskList);
    }
}
