package cleo.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** 
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Cleo: Got it. I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Removes a task from the list.
     * 
     * @param taskNumber the index of the task that will be removed.
     */
    public void removeTask(int taskNumber) {
        Task removedTask = tasks.remove(taskNumber);
        System.out.println("Cleo: Noted, I've removed this task:");
        System.out.println("    " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /** 
     * Returns the task at the specified index.
     * 
     * @param taskNumber the index of the task.
     * @return the task at the specified index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list as an integer.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints all the tasks in the list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Cleo: No tasks yet!");
        } else {
            System.out.println("Cleo: Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
