import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Cleo: Got it. I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void removeTask(int taskNumber) {
        Task removedTask = tasks.remove(taskNumber);
        System.out.println("Cleo: Noted, I've removed this task:");
        System.out.println("    " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public int size() {
        return tasks.size();
    }
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
