package cleo;

import java.io.IOException;

import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;
import javafx.application.Platform;

/**
 * Represents the Cleo chatbot and its tasks.
 * Provides functionality to add, mark, unmark, delete, and find tasks based on user input.
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
     * Processes the user input and returns an appropriate response.
     *
     * @param input The user input string.
     * @return The response from Cleo based on the command given.
     */

    public String getResponse(String input) {
        Parser.CommandType command = Parser.parseCommand(input.toLowerCase());
        assert command != null : "Command cannot be empty!";
        int taskNumber = 0;
        try {
            switch (command) {
            case BYE:
                Platform.exit();
                return Ui.getByeMessage();
            case HI:
                return Ui.getWelcomeMessage();
            case COMMANDS:
                return Ui.displayCommandsList();
            case LIST:
                return tasks.listTasks();
            case FIND:
                return tasks.findTask(input.substring(4).trim());
            case MARK:
                reply = markTask(input.substring(5).trim());
                storage.saveTasks(tasks);
                return reply;
            case UNMARK:
                reply = unmarkTask(input.substring(7).trim());
                storage.saveTasks(tasks);
                return reply;
            case DELETE:
                reply = deleteTasks(input.substring(7).trim());
                storage.saveTasks(tasks);
                return reply;
            case TODO:
                reply = addTodoTask(input.substring(4).trim());
                assert reply != null : "Reply should not be null";
                storage.saveTasks(tasks);
                return reply;
            case DEADLINE:
                reply = addDeadlineTask(input.substring(8).trim());
                assert reply != null : "Reply should not be null";
                storage.saveTasks(tasks);
                return reply;
            case EVENT:
                reply = addEventTask(input.substring(5).trim());
                assert reply != null : "Reply should not be null";
                storage.saveTasks(tasks);
                return reply;
            default:
                return Ui.showCommandError();
            }
        } catch (CleoException e) {
            return "Cleo: " + e.getMessage();
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param input The user input containing the task number to mark as done.
     * @return A response string indicating that the task has been marked as done.
     * @throws CleoException If the task number is invalid.
     */
    private String markTask(String input) throws CleoException {
        try {
            int taskNumber = Parser.parseTaskNumber(input, tasks.size());
            Task doneTask = tasks.getTask(taskNumber);
            doneTask.setDone();
            reply = Ui.getMarkMessage() + '\n' + doneTask.toString();
        } catch (CleoException e) {
            throw new CleoException("Please enter a valid task!");
        }
        return reply;
    }
    /**
     * Unmarks a task as not done based on user input.
     *
     * @param input The user input containing the task number to unmark.
     * @return A response string indicating that the task has been unmarked.
     * @throws CleoException If the task number is invalid.
     */
    private String unmarkTask(String input) throws CleoException {
        try {
            int taskNumber = Parser.parseTaskNumber(input, tasks.size());
            Task undoneTask = tasks.getTask(taskNumber);
            undoneTask.setUndone();
            reply = Ui.getUnmarkMessage() + '\n' + undoneTask.toString();
        } catch (CleoException e) {
            throw new CleoException("Please enter a  valid task!");
        }
        return reply;
    }
    /**
     * Deletes one or more tasks based on user input.
     *
     * @param input The user input containing the task numbers to delete.
     * @return A response string indicating the tasks that have been deleted.
     * @throws CleoException If the task numbers are invalid.
     */
    private String deleteTasks(String input) throws CleoException {
        try {
            reply = Ui.getDeleteMessage();
            int[] validTaskNumbers = Parser.parseTasksNumbers(input, tasks.size());
            for (int taskIndex : validTaskNumbers) {
                reply += '\n' + (tasks.getTask(taskIndex).toString());
            }
            tasks.removeTask(validTaskNumbers);
        } catch (CleoException e) {
            throw new CleoException("Please enter a  valid task!");
        }
        return reply + "\nTasks removed successfully!";
    }
    /**
     * Adds a new todo task based on user input.
     *
     * @param input The description of the todo task to add.
     * @return A response string confirming that the todo task has been added.
     * @throws CleoException If the task description is empty.
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
                tasksString += "\n" + task.toString();
            }
        } catch (CleoException e) {
            throw new CleoException("Please enter a todo description!");
        }
        return tasksString;
    }
    /**
     * Adds a new deadline task based on user input.
     *
     * @param input The description and deadline of the task, separated by '/by'.
     * @return A response string confirming that the deadline task has been added.
     * @throws CleoException If the description or deadline is missing.
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
            tasksString += "\n" + task.toString();
        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
        return tasksString;
    }
    /**
     * Adds a new event task based on user input.
     *
     * @param input The description, start time, and end time of the event, separated by '/from' and '/to'.
     * @return A response string confirming that the event task has been added.
     * @throws CleoException If the description or start/end times are missing.
     */
    private String addEventTask(String input) throws CleoException {
        String tasksString = "Cleo: Added event task(s)!";
        try {
            String[] parts = input.split("/from", 2);

            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new CleoException("Oops! The event description or date cannot be empty!");
            }
            String[] time = parts[1].split("/to", 2);
            if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            Events task = new Events(parts[0].trim(), time[0].trim(), time[1].trim());
            tasks.addTask(task);
            tasksString += "\n" + task.toString();

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
        new Cleo("./data/cleo.txt");
    }

}
