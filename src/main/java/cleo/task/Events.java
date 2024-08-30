package cleo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter printformatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /** 
     * Creates a new Events task.
     *
     * @param description A string containing the description of the event.
     * @param from A string containing the start time of the event in yyyy-MM-dd HH:mm format.
     * @param to A string containing the end time of the event in yyyy-MM-dd HH:mm format/
     */
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
    
    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event in MMM dd yyyy hh:mm a format.
     */
    public String getFrom() {
        return this.from.format(printformatter);
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event in MMM dd yyyy hh:mm a format.
     */
    public String getTo() {
        return this.to.format(printformatter);
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A string in the format "[E] description (from: start_time to: end_time)".
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}

