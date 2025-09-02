public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;  // Default is not to exit
    }
}
