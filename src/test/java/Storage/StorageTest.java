package Storage;

import TaskLists.TaskList;
import Tasks.ToDos;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;
    private TaskList taskList;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file to use for testing
        tempFilePath = Files.createTempFile("testTasks", ".txt");
        tempFilePath.toFile().deleteOnExit(); // Ensure the temp file is deleted after the test

        // Initialize Storage with the temporary file path
        storage = new Storage(tempFilePath.toString());

        // Initialize TaskList
        taskList = new TaskList();
    }

    @Test
    void testSaveTasks_SavesToFileCorrectly() throws IOException {
        // Arrange
        // Create a few tasks
        Task task1 = new ToDos("Test Todo");
        Task task2 = new Deadline("Test Deadline", "2025-09-01");
        Task task3 = new Events("Test Event", "10:00 AM", "11:00 AM");

        // Add tasks to TaskList
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        // Act
        // Save the tasks to the file using the Storage class
        storage.saveTasks(taskList);

        // Read the file contents
        String fileContent = new String(Files.readAllBytes(tempFilePath));

        // Assert
        // Check if the file contains the correct task data
        assertTrue(fileContent.contains("T | 0 | Test Todo"), "The file should contain the ToDo task");
        assertTrue(fileContent.contains("D | 0 | Test Deadline | 2025-09-01"), "The file should contain the Deadline task");
        assertTrue(fileContent.contains("E | 0 | Test Event | 10:00 AM to 11:00 AM"), "The file should contain the Event task");
    }

    @Test
    void testSaveTasks_SavesMultipleTasksCorrectly() throws IOException {
        // Arrange
        Task task1 = new ToDos("Another Todo");
        Task task2 = new ToDos("Second Todo");

        taskList.addTask(task1);
        taskList.addTask(task2);

        // Act
        storage.saveTasks(taskList);

        // Read the file contents
        String fileContent = new String(Files.readAllBytes(tempFilePath));

        // Assert
        assertTrue(fileContent.contains("T | 0 | Another Todo"), "The file should contain the first ToDo task");
        assertTrue(fileContent.contains("T | 0 | Second Todo"), "The file should contain the second ToDo task");
    }

    @Test
    void testSaveTasks_EmptyTaskList_DoesNotWriteAnything() throws IOException {
        // Act
        storage.saveTasks(taskList); // Save an empty task list

        // Read the file contents
        String fileContent = new String(Files.readAllBytes(tempFilePath));

        // Assert
        // Ensure the file is empty
        assertEquals("", fileContent, "The file should be empty when there are no tasks.");
    }
}
