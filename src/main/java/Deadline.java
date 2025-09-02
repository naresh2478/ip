
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;  // Store the deadline as a LocalDate

    // Constructor that accepts description and deadline as strings
    public Deadline(String description, String by) {
        super(description);

        // Parse the deadline string into LocalDate
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));  // Parse the date in "YYYY-MM-DD" format
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'YYYY-MM-DD' format for the deadline.");
        }
    }

    public LocalDate getDeadline() {
        return this.by;  // Return the LocalDate object
    }

    @Override
    public String toString() {
        // Format the deadline into the "MMM dd yyyy" format for display (e.g., "Oct 15 2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
