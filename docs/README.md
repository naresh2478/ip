# Jack User Guide

Welcome to **Jack**, your friendly personal task manager chatbot!

Jack helps you keep track of your todos, deadlines, and events, all from a simple chat interface. This guide will help you get started and make the most of Jack’s features.

---

## Quick Start

1. **Run Jack**
   - If using the GUI: Run the application using `./gradlew run` or by launching the main class in your IDE.
   - If using the CLI: Run the main class `Jack`.
2. **Start chatting!** Type your commands in the input box and Jack will respond.

---

## Features

### 1. Add a Todo
- **Command:** `todo <description>`
- **Example:** `todo read book`

### 2. Add a Deadline
- **Command:** `deadline <description> /by <yyyy-mm-dd HH:mm>`
- **Example:** `deadline submit report /by 2025-09-30 23:59`

### 3. Add an Event
- **Command:** `event <description> /at <yyyy-mm-dd HH:mm>`
- **Example:** `event project meeting /at 2025-10-01 14:00`

### 4. List All Tasks
- **Command:** `list`
- **Description:** Shows all your tasks with their status.

### 5. Mark a Task as Done
- **Command:** `mark <task number>`
- **Example:** `mark 2`

### 6. Unmark a Task
- **Command:** `unmark <task number>`
- **Example:** `unmark 2`

### 7. Delete a Task
- **Command:** `delete <task number>`
- **Example:** `delete 3`

### 8. Find Tasks by Keyword
- **Command:** `find <keyword>`
- **Example:** `find book`

### 9. Sort Deadlines Chronologically
- **Command:** `sortdeadlines`
- **Description:** Sorts all your deadlines by date.

### 10. Exit Jack
- **Command:** `bye`

---

## Command Summary Table

| Command        | Format/Example                              | Description                       |
|----------------|---------------------------------------------|-----------------------------------|
| todo           | todo read book                              | Add a todo task                   |
| deadline       | deadline submit report /by 2025-09-30 23:59 | Add a deadline task               |
| event          | event project meeting /at 2025-10-01 14:00  | Add an event                      |
| list           | list                                        | List all tasks                    |
| mark           | mark 2                                      | Mark task 2 as done               |
| unmark         | unmark 2                                    | Unmark task 2                     |
| delete         | delete 3                                    | Delete task 3                     |
| find           | find book                                   | Find tasks with 'book'            |
| sort deadlines | sort deadlines                              | Sort deadlines by date            |
| bye            | bye                                         | Exit the app                      |

---

## Tips
- Dates must be in the format `yyyy-mm-dd HH:mm` (e.g., 2025-09-30 23:59).
- Task numbers refer to the number shown in the `list` command.
- Jack saves your tasks automatically.

---

## Troubleshooting
- **Invalid command?** Jack will show an error message. Check your command format.
- **App not starting?** Ensure you have Java 11 or above installed.
- **Still stuck?** Check the [project README](../README.md) or contact the developer.

---

Enjoy being productive with **Jack**!

