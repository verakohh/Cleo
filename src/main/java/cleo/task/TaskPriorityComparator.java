package cleo.task;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        String priority1 = t1.getPriorityLevel();
        String priority2 = t2.getPriorityLevel();

        int p1 = Integer.parseInt(priority1.substring(1));
        int p2 = Integer.parseInt(priority2.substring(1));

        return Integer.compare(p1, p2);
    }
}
