package Commands;

import UI.Ui;
import TaskLists.TaskList;
import Storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;  // This will cause the main loop to terminate
    }
}
