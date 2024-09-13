package cleo.task;

import java.util.Comparator;

/**
 * A comparator class used to compare tasks based on their priority level.
 * It compares the priority levels of two tasks in ascending order, where
 * P0 is the highest priority and P4 is the lowest.
 */
public class TaskPriorityComparator implements Comparator<Task> {
    /**
     * Compares two tasks based on their priority levels.
     * Extracts the numeric value from the priority level (e.g., P0 -> 0, P1 -> 1) and compares them.
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer if the priority of t1 is less than,
     *         equal to, or greater than the priority of t2, respectively.
     */
    @Override
    public int compare(Task t1, Task t2) {
        String priority1 = t1.getPriorityLevel();
        String priority2 = t2.getPriorityLevel();

        // Extract the numeric value of the priority levels (e.g., P0 -> 0, P1 -> 1)
        int p1 = Integer.parseInt(priority1.substring(1));
        int p2 = Integer.parseInt(priority2.substring(1));

        return Integer.compare(p1, p2);
    }
}
