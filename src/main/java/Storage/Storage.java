package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import TaskLists.TaskList;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It provides methods to load tasks from a file into a TaskList and save the tasks
 * in a TaskList to a file. The file is specified by the filePath provided when the
 * Storage object is created.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file into the TaskList.
     * Skips malformed lines and prints warnings for errors.
     *
     * @param taskList The TaskList to populate with loaded tasks.
     */
    public void loadTasks(TaskList taskList) {
        try {
            // Create file and folder if they do not exist
            Path path = Paths.get(filePath);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            if (Files.exists(path)) {
                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(" \\| ");
                        if (parts.length < 3) {
                            continue; // Skip invalid lines
                        }
                        String type = parts[0]; // Type of task (T, D, E)
                        String status = parts[1]; // Task status (1 for done, 0 for not done)
                        String description = parts[2]; // Task description
                        boolean isDone = status.equals("1");
                        Task task = null;
                        switch (type) {
                        case "T":
                            task = new ToDos(description);
                            break;
                        case "D":
                            if (parts.length > 3) {
                                String deadline = parts[3];
                                task = new Deadline(description, deadline);
                            }
                            break;
                        case "E":
                            if (parts.length > 3) {
                                String fromTo = parts[3];
                                String[] timeParts = fromTo.split(" to ");
                                if (timeParts.length == 2) {
                                    task = new Events(description, timeParts[0], timeParts[1]);
                                }
                            }
                            break;
                        default:
                            break;
                        }
                        if (task != null) {
                            if (isDone) {
                                task.markAsDone();
                            }
                            taskList.addTask(task);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves tasks from TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to save.
     */
    public void saveTasks(TaskList taskList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                String taskStatus = task.isDone() ? "1" : "0";
                String taskDescription = task.getDescription();
                if (task instanceof ToDos) {
                    bw.write("T | " + taskStatus + " | " + taskDescription + "\n");
                } else if (task instanceof Deadline) {
                    bw.write("D | " + taskStatus + " | " + taskDescription + " | "
                            + ((Deadline) task).getDeadline() + "\n");
                } else if (task instanceof Events) {
                    bw.write("E | " + taskStatus + " | " + taskDescription + " | "
                            + ((Events) task).getFrom() + " to " + ((Events) task).getTo() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
