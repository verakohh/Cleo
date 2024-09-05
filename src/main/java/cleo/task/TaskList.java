package cleo.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Provides methods to add, remove, retrieve tasks,
 * and print the list of tasks. Tasks are stored internally in an ArrayList.
 */

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
     * Marks the task at the specified index as done.
     *
     * @param taskNumber is an integer for the task index.
     */
    public void setDone(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setDone();
        System.out.println("Cleo: Great job! I've marked this task as done:");
        System.out.println("    " + task);
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param taskNumber is an integer for the task index.
     */
    public void setUndone(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setUndone();
        System.out.println("Cleo: Okay! I've unmarked this task as not done yet:");
        System.out.println("    " + task);
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
