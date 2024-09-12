package cleo;

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

}
