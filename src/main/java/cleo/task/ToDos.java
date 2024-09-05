package cleo.task;
/**
 * Represents a ToDo task.
 * A ToDo task only has a description and no specific deadline or time associated with it.
 */
public class ToDos extends Task {
    protected String by;
    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the string description of the task.
     *
     * @return the string description of the task in the format "[T] description".
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

