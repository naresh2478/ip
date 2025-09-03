package Commands;

import Storage.Storage;
import TaskLists.TaskList;
import Tasks.Task;
import Tasks.ToDos;
import UI.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AddTodoCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private AddTodoCommand addTodoCommand;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize real instances for TaskList, Ui, and Storage
        taskList = new TaskList();
        ui = new Ui(); // Assuming you have a default constructor for Ui
        tempFilePath = Files.createTempFile("testTasks", ".txt");
        tempFilePath.toFile().deleteOnExit();
        storage = new Storage(tempFilePath.toString());

        // Clear the storage file before each test (optional, to avoid leftover data)
        storage.saveTasks(taskList); // Ensures the file starts empty
    }

    @Test
    void testExecute_ValidDescription_AddsTaskToTaskListAndSaves() {
        // Arrange
        String description = "Test Task";
        addTodoCommand = new AddTodoCommand(description);

        // Act
        addTodoCommand.execute(taskList, ui, storage);

        // Assert
        // Verify that the task was added to the task list
        Task expectedTask = new ToDos(description);
        assertTrue(taskList.getTasks().contains(expectedTask), "The task should be added to the task list");

//         To check if the task was saved to the storage, let's load the tasks from the storage
        TaskList loadedTaskList = new TaskList();
        storage.loadTasks(loadedTaskList); // Load tasks from the file
//
//        // Assert that the task is saved and reloaded correctly
        assertTrue(loadedTaskList.getTasks().contains(expectedTask), "The task should be saved and reloaded from storage");
    }

    @Test
    void testExecute_EmptyDescription_ThrowsIllegalArgumentException() {
        // Arrange
        addTodoCommand = new AddTodoCommand("");  // Empty description

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            addTodoCommand.execute(taskList, ui, storage);
        });

        // Check that the exception message is correct
        assertEquals("The description of a todo cannot be empty.", thrown.getMessage());
    }

    @Test
    void testExecute_NullDescription_ThrowsIllegalArgumentException() {
        // Arrange
        addTodoCommand = new AddTodoCommand(null);  // Null description

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            addTodoCommand.execute(taskList, ui, storage);
        });

        // Check that the exception message is correct
        assertEquals("The description of a todo cannot be empty.", thrown.getMessage());
    }
}
