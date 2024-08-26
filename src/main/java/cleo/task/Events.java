package cleo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter printformatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    public Events(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
            if (this.from.isBefore(LocalDateTime.now()) || this.to.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("The date(s) cannot be in the past!");
            }
            if (this.to.isBefore(this.from)) {
                throw new IllegalArgumentException("The end time of the event should be later than the start time!");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date and time format! Please use the format 'yyyy-MM-dd HH:mm'.");
        }
    }
    public String getFrom() {
        return this.from.format(printformatter);
    }
    public String getTo() {
        return this.to.format(printformatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}

