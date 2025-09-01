import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks from file into the TaskList
    public void loadTasks(TaskList taskList) {
        try {
            // Create file and folder if they do not exist
            Path path = Paths.get(filePath);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            if (Files.exists(path)) {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;

                // Read each line from the file
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) continue; // Skip invalid lines

                    String type = parts[0];  // Type of task (ToDo, Deadline, Event)
                    String status = parts[1];  // Task status (1 for done, 0 for not done)
                    String description = parts[2];  // Task description
                    boolean isDone = status.equals("1");  // Mark task as done or not

                    Task task = null;

                    // Create task based on the type
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
                                task = new Events(description, timeParts[0], timeParts[1]);
                            }
                            break;
                        default:
                            break;
                    }

                    // If a task is created, mark it as done if necessary
                    if (task != null) {
                        if (isDone) {
                            task.markAsDone();
                        }
                        taskList.addTask(task);  // Add task to the TaskList
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // Save tasks from TaskList to the file
    public void saveTasks(TaskList taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            // Write each task to the file in the required format
            for (Task task : taskList.getTasks()) {
                String taskStatus = task.isDone() ? "1" : "0";
                String taskDescription = task.getDescription();

                // Write the task in the correct format
                if (task instanceof ToDos) {
                    bw.write("T | " + taskStatus + " | " + taskDescription + "\n");
                } else if (task instanceof Deadline) {
                    bw.write("D | " + taskStatus + " | " + taskDescription + " | " + ((Deadline) task).getDeadline() + "\n");
                } else if (task instanceof Events) {
                    bw.write("E | " + taskStatus + " | " + taskDescription + " | " + ((Events) task).getFrom() + " to " + ((Events) task).getTo() + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
