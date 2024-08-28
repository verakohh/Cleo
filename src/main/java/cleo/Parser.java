package cleo;

public class Parser {

    public enum CommandType {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        HI,
        INVALID;
    }

    /**
     * Parse the user input to determine the command type.
     * @param userInput A string containing the user input
     * @return CommandType
     */

    public static CommandType parseCommand(String userInput) {
        if (userInput.startsWith("todo")) return CommandType.TODO;
        if (userInput.startsWith("deadline")) return CommandType.DEADLINE;
        if (userInput.startsWith("event")) return CommandType.EVENT;
        if (userInput.equals("list")) return CommandType.LIST;
        if (userInput.startsWith("mark")) return CommandType.MARK;
        if (userInput.startsWith("unmark")) return CommandType.UNMARK;
        if (userInput.startsWith("delete")) return CommandType.DELETE;
        if (userInput.equals("bye")) return CommandType.BYE;
        if (userInput.equals("hi") || userInput.equals("hello")) return CommandType.HI;
        return CommandType.INVALID;
    }

    /**
     * Parse the user input to determine the task number.
     * @param userInput A string containing the task number to be changed
     * @param taskListSize A integer representing the size of the task list
     * @return An integer representing the task number
     * @throws CleoException If the task number is out of range or not a valid integer
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
}
