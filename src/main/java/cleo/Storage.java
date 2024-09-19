package cleo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;

/**
 * Provides utility methods for storing and retrieving tasks stored onto user's device.
 */
public class Storage {
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_STATUS_DONE = "1";
    private static final String TASK_STATUS_NOT_DONE = "0";
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    private String filePath;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm a");

    /**
     * Creates a new Storage object with the specified file path.
     * Ensures that the data folder exists.
     * @param filePath The path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDataFolderExists();
    }

    /**
     * Ensures that the data folder exists, creating it if it doesn't.
     */
    private void ensureDataFolderExists() {
        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            logger.info("Data folder not found. Creating './data' directory now!");
        }
    }

    /**
     * Loads tasks from the specified file.
     * Converts the string into tasks. If the file isn't found, creates a new one.
     *
     * @return An array list of tasks.
     * @throws IOException if there is an issue loading the tasks.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        ensureDataFolderExists();

        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskContent = scanner.nextLine();
                tasks.add(parseTask(taskContent));
            }
            scanner.close();
        } else {
            logger.info("File not found. Creating a new file now!");
            createNewFile(file);
        }
        return tasks;
    }

    /**
     * Creates a new file and its parent directory if necessary.
     *
     * @param file The file to be created.
     * @throws IOException if the file cannot be created.
     */
    private void createNewFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Saves tasks from task list to the specified file.
     * Saved in a format that can be parsed later to recreate the Task objects.
     *
     * @param tasks The task list containing tasks to save.
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                fw.write(formatTaskForStorage(task));
            }
        } catch (IOException e) {
            logger.severe("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Formats a task for storage, depending on its type.
     *
     * @param task The task to format.
     * @return The formatted string representation of the task.
     */
    private String formatTaskForStorage(Task task) {
        if (task instanceof ToDos) {
            return String.format("T | %s | %s | %s\n", task.isDoneString() ? "1" : "0", task.getDescription(),
                    task.getPriorityLevel());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %s | %s | %s | %s\n", deadline.isDoneString() ? "1" : "0",
                    deadline.getDescription(), deadline.storeGetBy(), deadline.getPriorityLevel());
        } else if (task instanceof Events) {
            Events event = (Events) task;
            return String.format("E | %s | %s | %s | %s | %s\n", event.isDoneString() ? "1" : "0",
                    event.getDescription(), event.storeGetFrom(), event.storeGetTo(), event.getPriorityLevel());
        }
        return "";
    }

    /**
     * Parses the task string and converts it back into a Task object.
     *
     * @param taskContent The task content string to parse.
     * @return The corresponding Task object.
     * @throws FileNotFoundException if the task content is invalid.
     */
    private Task parseTask(String taskContent) throws FileNotFoundException {
        String[] parts = taskContent.split(" \\| ");
        String type = parts[0];
        String done = parts[1];
        String desc = parts[2];
        String priority = parts[parts.length - 1];
        Task task = null;

        switch (type) {
        case TASK_TYPE_TODO:
            task = new ToDos(desc, priority);
            break;
        case TASK_TYPE_DEADLINE:
            task = new Deadline(desc, parts[3], priority);
            break;
        case TASK_TYPE_EVENT:
            task = new Events(desc, parts[3], parts[4], priority);
            break;
        default:
            throw new FileNotFoundException("Invalid task type in file");
        }

        if (done.equals(TASK_STATUS_DONE)) {
            task.setDone();
        }
        return task;
    }
}
