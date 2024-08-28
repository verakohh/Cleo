package cleo.task;


public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon based on the value of the `isDone` flag.
     *
     * @return the status icon as a string, either "[X] " if `isDone` is true, or "[ ] " if `isDone` is false
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the value of the `isDone` flag.
     * 
     * @return the value of the `isDone` flag
     */
    public Boolean isDoneString() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }
    
    /**
     * Unmarks the tasks as done.
     */
    public void unmarkAsDone(){
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task
     * 
     * @return a string representation of the taskn in the format "[is_done] description"
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
