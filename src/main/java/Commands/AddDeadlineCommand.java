package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Tasks.Task;
import Tasks.Deadline;  // assuming Deadline extends Task
import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;


public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
//        if (description == null || description.trim().isEmpty()) {
//            throw new IllegalArgumentException("The description of a deadline cannot be empty.");
//        }

//        if (deadline == null || deadline.trim().isEmpty()) {
//            throw new IllegalArgumentException("Please provide a valid deadline.");
//        }

        try {
            // Validate the deadline date format
            LocalDate parsedDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Deadline(description, parsedDate.toString());
            taskList.addTask(task);
            System.out.println("Got it. I've added this deadline:\n" + task);
            storage.saveTasks(taskList);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format for the deadline.");
        }
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
