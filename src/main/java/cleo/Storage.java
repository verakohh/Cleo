package cleo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;

/**
 * Provides utility methods for storing and retrieiving
 * tasks stored onto user's device.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage object with the specified file path.
     * Ensures that the data folder exists.
     * @param filePath
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
            dataFolder.mkdirs(); // Create the folder and any necessary parent directories
            System.out.println("Data folder not found. Creating './data' directory now!");
        }
    }

    /**
     * Loads tasks from the specified file.
     * Converts the string into tasks. If file isn't found, creates a new one.
     *
     * @return An array list of tasks.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        ensureDataFolderExists();

        if (file.exists()) {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskContent = s.nextLine();
                tasks.add(parseTask(taskContent));
            }
            s.close();
        } else {
            System.out.println("File not found. Creating a new file now!");
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return tasks;
    }


    /**
     * Saves tasks from task list to the specified file.
     * Saved in a format that can be parsed later to recreat the Task objects.
     *
     * @param tasks An array list of tasks.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                if (task instanceof ToDos) {
                    fw.write("T | " + (task.isDoneString() ? "1" : "0") + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    fw.write("D | " + (deadline.isDoneString() ? "1" : "0")
                            + " | " + deadline.getDescription() + " | " + deadline.getBy() + "\n");
                } else if (task instanceof Events) {
                    Events event = (Events) task;
                    fw.write("E | " + (event.isDoneString() ? "1" : "0")
                            + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Returns a task based on the file content in specified file.
     *
     * @param taskContent is a string that contains the task.
     * @return a new task made from the string.
     * @throws FileNotFoundException if file is not found.
     */
    private Task parseTask(String taskContent) throws FileNotFoundException {
        String[] parts = taskContent.split(" \\| ");
        String type = parts[0];
        String done = parts[1];
        String desc = parts[2];
        Task task = null;

        if (type.equals("T")) {
            task = new ToDos(desc);
        } else if (type.equals("D")) {
            task = new Deadline(desc, parts[3]);
        } else if (type.equals("E")) {
            task = new Events(desc, parts[3], parts[4]);
        }
        if (done.equals("1")) {
            task.setDone();
        }
        return task;
    }
}


