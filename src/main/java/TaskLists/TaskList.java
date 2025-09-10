package TaskLists;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Delete a task from the list
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    // Get all tasks in the list
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Get task count
    public int getTaskCount() {
        return tasks.size();
    }

    // Mark a task as done
    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    // Unmark a task as not done
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
