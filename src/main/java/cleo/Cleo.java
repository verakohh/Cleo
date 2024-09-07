package cleo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;

/**
 * Represents the Cleo chatbot and its tasks.
 */
public class Cleo {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private String reply;

    /**
     * Loads the available tasks file if there is in the folder.
     *
     * @param filePath is a string that contains path of file that store taskslist.
     */
    public Cleo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the Cleo application, taking user input and executing corresponding commands.
     *
     */
    public void run() {
        ui.displayWelcomeMessage();
    }
    public String getResponse(String input) {
        Parser.CommandType command = Parser.parseCommand(input);
        int taskNumber = 0;
        try {
            switch (command) {
            case BYE:
                return "Cleo: Goodbye, hope to see you again soon! :)";
            case HI:
                return "Cleo: Hi there! How can I help you today?:)";
            case LIST:
                return tasks.listTasks();
            case FIND:
                return tasks.findTask(input.substring(4).trim());
            case MARK:
                taskNumber = Parser.parseTaskNumber(input.substring(5), tasks.size());
                tasks.getTask(taskNumber).setDone();
                storage.saveTasks(tasks);
                return "Cleo: Task marked as done!";
            case UNMARK:
                taskNumber = Parser.parseTaskNumber(input.substring(7), tasks.size());
                tasks.getTask(taskNumber).setUndone();
                storage.saveTasks(tasks);
                return "Cleo: Task unmarked!";
            case DELETE:
                String[] taskNumbers = input.substring(7).trim().split("\\s");
                List<Integer> validTaskNumbers = new ArrayList<>();
                for (String taskIndex : taskNumbers) {
                    try {
                        Integer validTaskNumber = Parser.parseTaskNumber(taskIndex, tasks.size());
                        validTaskNumbers.add(validTaskNumber);
                    } catch (CleoException e) {
                        return "Cleo: " + e.getMessage();
                    }
                }
                validTaskNumbers.sort(Collections.reverseOrder());
                for (int taskIndex : validTaskNumbers) {
                    tasks.removeTask(taskIndex);
                }
                storage.saveTasks(tasks);
                return "Cleo: Task(s) deleted!";
            case TODO:
                reply = addTodoTask(input.substring(4).trim());
                storage.saveTasks(tasks);
                return reply;
            case DEADLINE:
                reply = addDeadlineTask(input.substring(8).trim());
                storage.saveTasks(tasks);
                return reply;
            case EVENT:
                reply = addEventTask(input.substring(5).trim());
                storage.saveTasks(tasks);
                return reply;
            case INVALID:
                return "Cleo: Invalid command!";
            default:
                return "Cleo: Unrecognized command!";
            }
        } catch (CleoException e) {
            return "Cleo: " + e.getMessage();
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param input A string containing the description of the todo task to be added.
     * @throws CleoException if the input description is empty.
     */
    private String addTodoTask(String input) throws CleoException {
        String tasksString = "Cleo: Added todo task(s)!";
        String[] todos = input.split(";");
        try {
            for (String todo : todos) {
                if (todo.isEmpty()) {
                    throw new CleoException("Oops! The description of a todo cannot be empty.");
                }
                ToDos task = new ToDos(todo);
                tasks.addTask(task);
                tasksString += "\n" + task.toString() ;
            }
        } catch (CleoException e) {
            throw new CleoException("Please enter a todo description!");
        }
        return tasksString;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param input A string containing the description and deadline of the task, separated by '/by'.
     * @throws CleoException If the deadline description or date is empty, or if the deadline is in the past.
     */
    private String addDeadlineTask(String input) throws CleoException {
        String tasksString = "Cleo: Added deadline task(s)!";
        try {
            String[] parts = input.split("/by", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CleoException("Oops! The deadline description or date cannot be empty!");
            }
            Deadline task = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.addTask(task);
            tasksString += "\n" + task.toString() ;
        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
        return tasksString;
    }
    /**
     * Adds an event task to the task list.
     *
     * @param input A string containing the description of the event, and start and end times,
     *          separated by '/from' and '/to'.
     * @throws CleoException If the event description, start time, or end time is empty,
     *          or if the start time is after the end time.
     */
    private String addEventTask(String input) throws CleoException {
        String tasksString = "Cleo: Added deadline task(s)!";
        try {
            String[] parts = input.split("/from", 2);

            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            String[] time = parts[1].split("/to", 2);
            if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            Events task = new Events(parts[0].trim(), time[0].trim(), time[1].trim());
            tasks.addTask(task);
            tasksString += "\n" + task.toString() ;

        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
        return tasksString;
    }

    /**
     * Runs the main entry point of the Cleo application.
     *
     * @param  args  Command line arguments (not used in this implementation).
     * @throws CleoException if an error occurs during the execution of the Cleo application.
     */
    public static void main(String[] args) throws CleoException {
        new Cleo("./data/cleo.txt").run();
    }

}
