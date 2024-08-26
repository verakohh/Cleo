package cleo.task;


public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getDescription() {
        return this.description;
    }
    public Boolean isDoneString() {
        return this.isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }
    public void unmarkAsDone(){
        this.isDone = false;
    }
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
