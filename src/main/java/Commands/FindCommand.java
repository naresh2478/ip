package Commands;

import Exceptions.JackException;
import Storage.Storage;
import TaskLists.TaskList;
import UI.Ui;
/**
 * The FindCommand class represents a command to find tasks in the task list that match a given keyword.
 * It takes a keyword and searches the TaskList for tasks containing that keyword.
 * This class extends the Command class.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        var foundTasks = taskList.find(keyword);
        return ui.showFind(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
