package Commands;

import TaskLists.TaskList;
import UI.Ui;
import Storage.Storage;

public class ListCommand extends Command {

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
