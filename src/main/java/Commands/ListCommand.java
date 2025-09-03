package Commands;

import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;

/**
 * The ListCommand class represents a command to display all tasks in the task list.
 * It retrieves and prints the list of tasks from the provided task list.
 * This class extends the Command class.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand. This method retrieves all tasks from the task list
     * and displays them to the user.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The UI object used to interact with the user (though not used in this method).
     * @param storage The storage object (though not used in this method).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are all the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + taskList.getTasks().get(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
