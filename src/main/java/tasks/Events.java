package tasks;

public class Events extends Task {

    protected String start;
    protected String end;

    public Events(String description, String start, String end) {
        super(description);
        assert start != null && !start.trim().isEmpty() : "Start time must not be empty";
        assert end != null && !end.trim().isEmpty() : "End time must not be empty";
        this.start = start;
        this.end = end;
    }

    public String getFrom() {
        return this.start;
    }

    public String getTo() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
