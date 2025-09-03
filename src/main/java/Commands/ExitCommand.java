package Commands;

import UI.Ui;
import TaskLists.TaskList;
import Storage.Storage;

/**
 * The ExitCommand class represents a command to exit the program.
 * It triggers the display of an exit message and indicates that the program should terminate.
 * This class extends the Command class.
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand. This method shows the exit message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;  // This will cause the main loop to terminate
    }
}
