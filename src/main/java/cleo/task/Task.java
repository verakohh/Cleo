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

    public void setDone(){
        this.isDone = true;
    }
    public void setUndone(){
        this.isDone = false;
    }
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
