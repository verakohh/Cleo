import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDataFolderExists();

    }
    private void ensureDataFolderExists() {
        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // Create the folder and any necessary parent directories
            System.out.println("Data folder not found. Creating './data' directory now!");
        }
    }

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

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i< tasks.size(); i++) {
                Task task = tasks.getTask(i);
                if (task instanceof ToDos) {
                    fw.write("T | " + (task.isDoneString() ? "1" : "0") + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    fw.write("D | " + (deadline.isDoneString() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy() + "\n");
                } else if (task instanceof Events) {
                    Events event = (Events) task;
                    fw.write("E | " + (event.isDoneString() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

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
            task.markAsDone();
        }
        return task;
    }
}


