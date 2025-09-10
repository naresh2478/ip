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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        var foundTasks = taskList.find(keyword);
        return ui.showFind(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;  // This command doesn't cause the program to exit
    }
}
