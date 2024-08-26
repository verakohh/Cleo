package cleo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter printformatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");


    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
            if (this.by.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("The deadline cannot be in the past!");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date and time format! Please use the format 'yyyy-MM-dd HH:mm'.");
        }
    }

    public String getBy() {
        return this.by.format(printformatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getBy() + ")";
    }


}
