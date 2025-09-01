public class Parser {

    // Parse user input and return the corresponding Command
    public static Command parse(String userInput) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "todo":
                return new AddTodoCommand(parts[1]);
            case "deadline":
                return parseDeadline(parts[1]);
            case "event":
                return parseEvent(parts[1]);
            case "mark":
                return new MarkTaskCommand(Integer.parseInt(parts[1]) - 1);
            case "unmark":
                return new UnmarkTaskCommand(Integer.parseInt(parts[1]) - 1);
            case "delete":
                return new DeleteTaskCommand(Integer.parseInt(parts[1]) - 1);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Invalid command: " + command);
        }
    }

    private static Command parseDeadline(String input) throws DukeException {
        String[] parts = input.split("/by");
        if (parts.length < 2) {
            throw new DukeException("Please provide a description and a deadline.");
        }
        return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private static Command parseEvent(String input) throws DukeException {
        String[] parts = input.split("/from");
        if (parts.length < 2) {
            throw new DukeException("Please provide a description and a time period.");
        }
        String[] timeParts = parts[1].split("/to");
        if (timeParts.length < 2) {
            throw new DukeException("Please provide start and end times for the event.");
        }
        return new AddEventCommand(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }
}
