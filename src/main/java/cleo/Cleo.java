package cleo;

import java.io.IOException;

import cleo.task.CommandSuggestions;
import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;
import javafx.application.Platform;
import javafx.util.Pair;

/**
 * Represents the Cleo chatbot and its tasks.
 * Provides functionality to add, mark, unmark, delete, and find tasks based on user input.
 */
public class Cleo {
    private static final String DEFAULT_PRIORITY = "P4";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private String reply;

    /**
     * Constructor for Cleo.
     * Loads tasks from the specified file if available.
     *
     * @param filePath the file path to load tasks from.
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

        try {
            switch (command) {
            case BYE:
                return handleExit();
            case HI:
                return Ui.getWelcomeMessage();
            case COMMANDS:
                return Ui.displayCommandsList();
            case LIST:
                return handleListTasks();
            case FIND:
                return handleFindTask(input);
            case MARK:
                return handleMarkTask(input);
            case UNMARK:
                return handleUnmarkTask(input);
            case DELETE:
                return handleDeleteTask(input);
            case TODO:
                return handleAddTodoTask(input);
            case DEADLINE:
                return handleAddDeadlineTask(input);
            case EVENT:
                return handleAddEventTask(input);
            case PRIORITY:
                return handleUpdatePriority(input);
            default:
                return handleInvalidCommand(input);
            }
        } catch (CleoException e) {
            return "Cleo: " + e.getMessage();
        }
    }

    /**
     * Handles the exit command.
     *
     * @return the exit message.
     */
    private String handleExit() {
        Platform.exit();
        return Ui.getByeMessage();
    }

    /**
     * Lists tasks, sorted by priority.
     *
     * @return a list of tasks.
     */
    private String handleListTasks() {
        tasks.listSortedByPriority();
        return tasks.listTasks();
    }

    /**
     * Finds a task based on user input.
     *
     * @param input the user input.
     * @return the matching tasks.
     */
    private String handleFindTask(String input) {
        try {
            return tasks.findTask(input.substring(4).trim());
        } catch (CleoException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param input the user input.
     * @return the message indicating the task is marked as done.
     * @throws CleoException if task number is invalid.
     */
    private String handleMarkTask(String input) throws CleoException {
        int taskNumber = Parser.parseTaskNumber(input.substring(5).trim(), tasks.size());
        Task doneTask = tasks.getTask(taskNumber);
        doneTask.setDone();
        storage.saveTasks(tasks);
        return Ui.getMarkMessage() + '\n' + doneTask.toString();
    }

    /**
     * Unmarks a task as not done based on user input.
     *
     * @param input the user input.
     * @return the message indicating the task is unmarked.
     * @throws CleoException if task number is invalid.
     */
    private String handleUnmarkTask(String input) throws CleoException {
        int taskNumber = Parser.parseTaskNumber(input.substring(7).trim(), tasks.size());
        Task undoneTask = tasks.getTask(taskNumber);
        undoneTask.setUndone();
        storage.saveTasks(tasks);
        return Ui.getUnmarkMessage() + '\n' + undoneTask.toString();
    }

    /**
     * Deletes tasks based on user input.
     *
     * @param input the user input.
     * @return the message indicating the tasks have been deleted.
     * @throws CleoException if task numbers are invalid.
     */
    private String handleDeleteTask(String input) throws CleoException {
        int[] validTaskNumbers = Parser.parseTasksNumbers(input.substring(7).trim(), tasks.size());
        StringBuilder reply = new StringBuilder(Ui.getDeleteMessage());
        for (int taskIndex : validTaskNumbers) {
            reply.append("\n").append(tasks.getTask(taskIndex).toString());
        }
        tasks.removeTask(validTaskNumbers);
        storage.saveTasks(tasks);
        return reply + "\nTasks removed successfully!";
    }

    /**
     * Adds a todo task based on user input.
     *
     * @param input the user input.
     * @return the message indicating the todo task has been added.
     * @throws CleoException if task description is invalid.
     */
    private String handleAddTodoTask(String input) throws CleoException {
        StringBuilder tasksString = new StringBuilder("Cleo: Added todo task(s)!");

        String[] todos = input.substring(4).trim().split(";");
        for (String todo : todos) {
            Pair<String, String> parsedTask = Parser.parseTodoInput(todo);
            String description = parsedTask.getKey();
            String priorityLevel = parsedTask.getValue() != null ? parsedTask.getValue() : DEFAULT_PRIORITY;
            ToDos task = new ToDos(description, priorityLevel);
            tasks.addTask(task);
            tasksString.append("\n").append(task.toString());
        }
        storage.saveTasks(tasks);
        return tasksString.toString();
    }

    /**
     * Adds a deadline task based on user input.
     *
     * @param input the user input.
     * @return the message indicating the deadline task has been added.
     * @throws CleoException if task description or deadline is invalid.
     */
    private String handleAddDeadlineTask(String input) throws CleoException {
        String[] parsedData = Parser.parseDeadlineInput(input.substring(8).trim());
        Deadline task = new Deadline(parsedData[0], parsedData[1],
                parsedData[2] != null ? parsedData[2] : DEFAULT_PRIORITY);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return "Cleo: Added deadline task(s)!\n" + task.toString();
    }

    /**
     * Adds an event task based on user input.
     *
     * @param input the user input.
     * @return the message indicating the event task has been added.
     * @throws CleoException if task description or time range is invalid.
     */
    private String handleAddEventTask(String input) throws CleoException {
        String[] parsedData = Parser.parseEventInput(input.substring(5).trim());
        Events task = new Events(parsedData[0], parsedData[1], parsedData[2],
                parsedData[3] != null ? parsedData[3] : DEFAULT_PRIORITY);
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return "Cleo: Added event task(s)!\n" + task.toString();
    }

    /**
     * Updates a task's priority based on user input.
     *
     * @param input the user input.
     * @return the message indicating the task's priority has been updated.
     * @throws CleoException if task number or priority level is invalid.
     */
    private String handleUpdatePriority(String input) throws CleoException {
        String[] cleanInput = input.substring(8).split("#");
        if (cleanInput.length < 2 || cleanInput[1].trim().isEmpty()) {
            throw new CleoException("Oops! Please specify a priority level for the task!");
        }
        int taskNumber = Parser.parseTaskNumber(cleanInput[0].trim(), tasks.size());
        String taskPriority = Parser.parsePriority(cleanInput[1].trim());
        Task updateTask = tasks.getTask(taskNumber);
        updateTask.setPriorityLevel(taskPriority);
        storage.saveTasks(tasks);
        return Ui.getUpdatePriorityMessage() + '\n' + updateTask.toString();
    }

    /**
     * Handles invalid commands by suggesting a similar valid command.
     *
     * @param input the user input.
     * @return the suggestion or invalid command message.
     */
    private String handleInvalidCommand(String input) {
        String suggestedCommand = CommandSuggestions.getClosestCommand(input.toLowerCase());
        if (suggestedCommand != null) {
            return "Cleo: Invalid command! Did you mean '" + suggestedCommand + "' instead?";
        } else {
            return Ui.showCommandError();
        }
    }

    /**
     * Runs the main entry point of the Cleo application.
     *
     * @param args command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Cleo("./data/cleo.txt");
    }
}
