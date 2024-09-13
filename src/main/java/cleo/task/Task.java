package cleo.task;

/**
 * Represents an abstract task.
 * A task contains a description and a status indicating whether it is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String priorityLevel;
    /**
     *  Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description, String priorityLevel) {
        this.description = description;
        assert description != null : "Description should not be empty!";
        this.isDone = false;
        assert !isDone : "Task should not be marked done yet.";
        this.priorityLevel = priorityLevel;
        assert priorityLevel != null : "Priority should not be empty";
    }

    /**
     * Returns the status icon based on the value of the `isDone` flag.
     *
     * @return the status icon as a string, either "[X] " if `isDone` is true, or "[ ] " if `isDone` is false.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the value of the `isDone` flag.
     *
     * @return the value of the `isDone` flag.
     */
    public boolean isDoneString() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }
    /**
     * Marks the tasks as not done.
     */
    public void setUndone() {
        isDone = false;
    }
    /**
     * Returns the priority level of the task as a string.
     *
     * @return the priority level of the task.
     */
    public String getPriorityLevel() {
        return this.priorityLevel;
    }
    /**
     * Sets the priority level of the task.
     *
     * @param priorityLevel The new priority level.
     */
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task in the format "[is_done] description".
     */
    @Override
    public String toString() {
        return getStatusIcon() + description + " (" + priorityLevel + ")";
    }
}
