
import java.util.Scanner;

public class Jack {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Jack(String filePath) {
        ui = new Ui();  // Initialize the UI class
        taskList = new TaskList();  // Initialize the TaskList class
        storage = new Storage(filePath);  // Initialize the Storage class

        // Load tasks from storage into the TaskList
        storage.loadTasks(taskList);
    }

    public void run() {
        ui.showWelcome();  // Show welcome message

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();  // Read the user command
                // Parse the command using Parser and execute it
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);

                // Check if the command should exit the program
                isExit = c.isExit();

            } catch (Exception e) {
                ui.showError(e.getMessage());  // Show error message
            }
        }
    }

    public static void main(String[] args) {
        // Initialize Jack with the file path to store tasks
        new Jack("data/Jack.txt").run();  // Start the application
    }
}



