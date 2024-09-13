package cleo;

import javafx.util.Pair;

/**
 * Handles parsing of user input and provides command types and task number extraction
 * for the Cleo chatbot.
 */
public class Parser {
    /**
     * Enum representing the different command types available in Cleo.
     */
    public enum CommandType {
        LIST,
        FIND,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        HI,
        COMMANDS,
        PRIORITY,
        INVALID;
    }

    /**
     * Parses the user input to determine the command type.
     *
     * @param userInput A string containing the user's input.
     * @return CommandType The type of command determined from the user input.
     */
    public static CommandType parseCommand(String userInput) {
        if (userInput.startsWith("todo")) {
            return CommandType.TODO;
        }
        if (userInput.startsWith("find")) {
            return CommandType.FIND;
        }
        if (userInput.startsWith("deadline")) {
            return CommandType.DEADLINE;
        }
        if (userInput.startsWith("event")) {
            return CommandType.EVENT;
        }
        if (userInput.equals("list")) {
            return CommandType.LIST;
        }
        if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        }
        if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        }
        if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        }
        if (userInput.equals("bye")) {
            return CommandType.BYE;
        }
        if (userInput.equals("hi") || userInput.equals("hello")) {
            return CommandType.HI;
        }
        if (userInput.equals("commands")) {
            return CommandType.COMMANDS;
        }
        if (userInput.startsWith("priority")) {
            return CommandType.PRIORITY;
        }
        return CommandType.INVALID;
    }

    /**
     * Parses the user input to extract a single task number.
     *
     * This method converts the user's input into an integer, subtracting 1 to match the internal
     * zero-based indexing of the task list. It also ensures that the task number provided
     * is valid (i.e., within the range of the current task list).
     *
     * @param userInput A string containing the task number to be changed.
     * @param taskListSize An integer representing the size of the task list.
     * @return An integer representing the task number (0-based index).
     * @throws CleoException If the task number is out of range or not a valid integer.
     */
    public static int parseTaskNumber(String userInput, int taskListSize) throws CleoException {
        try {
            int taskNumber = Integer.parseInt(userInput) - 1;
            if (taskNumber < 0 || taskNumber >= taskListSize) {
                throw new CleoException("Please provide a valid task number.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new CleoException("Please provide a valid task number.");
        }
    }
    /**
     * Parses the user input to extract multiple task numbers.
     *
     * This method splits the user input by spaces to extract multiple task numbers and
     * ensures that each number is valid by checking that it falls within the bounds of the task list.
     *
     * @param userInput A string containing multiple task numbers separated by spaces.
     * @param taskListSize An integer representing the size of the task list.
     * @return An array of integers representing the task numbers (0-based indices).
     * @throws CleoException If any of the task numbers are out of range or not valid integers.
     */
    public static int[] parseTasksNumbers(String userInput, int taskListSize) throws CleoException {
        try {
            String[] inputTaskNumbers = userInput.split("\\s");
            int[] taskNumbers = new int[inputTaskNumbers.length];

            for (int i = 0; i < taskNumbers.length; i++) {
                taskNumbers[i] = Integer.parseInt(inputTaskNumbers[i]) - 1;
                if (taskNumbers[i] < 0 || taskNumbers[i] >= taskListSize) {
                    throw new CleoException("Please provide a valid task number.");
                }
            }
            return taskNumbers;
        } catch (NumberFormatException e) {
            throw new CleoException("Please provide a valid task number.");
        }
    }
    /**
     * Parses the input string for a todo task.
     *
     * @param input The raw input string for a todo.
     * @return A string representing the todo description.
     * @throws CleoException If the description is empty.
     */
    public static Pair<String, String> parseTodoInput(String input) throws CleoException {
        input = input.trim();
        String[] parts = input.split("#");
        String description = parts[0].trim();

        if (description.isEmpty()) {
            throw new CleoException("Oops! The description of a todo cannot be empty!");
        }

        String priority = (parts.length > 1) ? parsePriority(parts[1].trim()) : null;
        return new Pair<>(description, priority);
    }


    /**
     * Parses the input string for a deadline task.
     *
     * @param input The raw input string for a deadline, which should contain a '/by' separator.
     * @return An array of strings containing the description and deadline.
     * @throws CleoException If the input is invalid or missing required parts.
     */
    public static String[] parseDeadlineInput(String input) throws CleoException {
        String[] parts = input.split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CleoException("Oops! The deadline description or date cannot be empty!");
        }

        String description = parts[0].trim();
        String deadline = parts[1].split("#")[0].trim(); // Split to remove the priority from deadline if present
        String priority = (parts[1].contains("#")) ? parsePriority(parts[1].split("#")[1].trim()) : null;

        return new String[]{description, deadline, priority};
    }


    /**
     * Parses the input string for an event task.
     *
     * @param input The raw input string for an event, which should contain '/from' and '/to' separators.
     * @return An array of strings containing the description, start time, and end time.
     * @throws CleoException If the input is invalid or missing required parts.
     */
    public static String[] parseEventInput(String input) throws CleoException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new CleoException("Oops! The event description or date cannot be empty!");
        }

        String[] time = parts[1].split("/to", 2);
        if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
            throw new CleoException("Oops! Please specify both start and end times for the event!");
        }

        String description = parts[0].trim();
        String startTime = time[0].trim();
        String endTime = time[1].split("#")[0].trim(); // Split to remove the priority from time if present
        String priority = (time[1].contains("#")) ? parsePriority(time[1].split("#")[1].trim()) : null;

        return new String[]{description, startTime, endTime, priority};
    }
    /**
     * Parses the input string to extract the priority level for a task.
     * Ensures that the priority level is valid (between P0 and P4).
     *
     * @param input The user input containing the priority level (e.g., "P0", "P1", etc.).
     * @return A valid priority level string (e.g., "P0", "P1").
     * @throws CleoException If the priority level is not between P0 and P4 or the input is invalid.
     */
    public static String parsePriority(String input) throws CleoException {
        input = input.trim();
        if (!input.matches("P[0-4]")) {
            throw new CleoException("Invalid priority level! Priority must be between P0 and P4.");
        }
        return input;
    }


}
