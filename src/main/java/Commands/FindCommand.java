package Commands;

import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;


public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
//        if (keyword == null || keyword.trim().isEmpty()) {
//            throw new IllegalArgumentException("Please provide a keyword to search for.");
//        }
        boolean isFound = false;  // Flag to check if any task matches the search keyword

        System.out.println("Here are the matching tasks in your list:");

        int counter = 1;

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            String description = taskList.getTasks().get(i).getDescription();
            if (description.toLowerCase().contains(keyword.toLowerCase())) {
                // Display the matching task
                System.out.println(counter + ". " + taskList.getTasks().get(i).toString());
                isFound = true;
                counter++;
            }
        }
        if (!isFound) {
            System.out.println("No tasks found matching the keyword: " + keyword);
        }
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
