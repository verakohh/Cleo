package cleo;
/**
 * Represents the user interface of the Cleo chatbot.
 * This class provides methods to display messages for various user interactions such as commands, greeting messages,
 * and other prompts used by the chatbot.
 */
public class Ui {
    /**
     * Returns a greeting message when the user enters the 'hi' or 'hello' command.
     *
     * @return A greeting message from Cleo.
     */
    public static String displayCommandsList() {
        return """
        Here are the available commands:
        1. TODO [task description] - Adds a todo task (e.g. "todo call mom #P2").
           * You can add multiple todos at once by separating them with a semicolon (e.g. "todo call;bathe;study").
        2. DEADLINE [task description] /by [date] - Adds a deadline task
           (e.g. "deadline submit work /by 2025-01-01 11:00").
        3. EVENT [task description] /from [start time] /to [end time] - Adds an event task
           (e.g. "event team meeting /from 2025-01-01 09:00 /to 2025-01-01 11:00 #P2 ").
        4. LIST - Lists all the tasks you've added.
        5. MARK [task number] - Marks a task as done (e.g. "mark 2").
        6. UNMARK [task number] - Marks a task as not done (e.g. "unmark 2").
        7. DELETE [task numbers] - Deletes tasks by their numbers (e.g. "delete 1 3 4").
           * You can delete multiple tasks at once by separating task numbers with spaces.
        8. FIND [keyword] - Finds tasks by a keyword (e.g. "find book", "find [E]").
        9. PRIORITY [task number] [#priority level] - Changes priority level of the task. choose from P[0-4]
           (e.g. priority 2 #P0)
        10. BYE - Exits the application.
            """;
    }

    /**
     * Returns a greeting message when the user enters the 'hi' or 'hello' command.
     *
     * @return A greeting message from Cleo.
     */
    public static String getHelloMessage() {
        String greeting = "Cleo: Hi there! How can I help you today?:)";
        return greeting;
    }
    /**
     * Returns a farewell message when the user enters the 'bye' command.
     *
     * @return A farewell message from Cleo.
     */
    public static String getByeMessage() {
        String goodbye = "Cleo: Goodbye, hope to see you again soon! :)";
        return goodbye;
    }
    /**
     * Displays the welcome message when Cleo is started.
     * This includes a brief greeting and instruction on how to access the command list.
     *
     * @return The welcome message along with an instruction to access the command list.
     */
    public static String getWelcomeMessage() {
        return ("Good day! I’m Cleo, your personal assistant,"
                + " what’s on the agenda today?\nType 'commands' for the list of commands!");
    }
    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @return A confirmation message after marking a task as done.
     */
    public static String getMarkMessage() {
        return "Cleo: Task marked as done!";
    }
    /**
     * Returns a message confirming that a task has been unmarked (marked as not done).
     *
     * @return A confirmation message after unmarking a task.
     */
    public static String getUnmarkMessage() {
        return "Cleo: Task unmarked!";
    }
    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @return A confirmation message after deleting a task.
     */
    public static String getDeleteMessage() {
        return "Cleo: Noted, I've removed this task:";
    }
    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @return A confirmation message after deleting a task.
     */
    public static String getUpdatePriorityMessage() {
        return "Cleo: Noted, I've updated the priority for this task:";
    }
    /**
     * Displays an error message when there is an issue loading the command.
     */
    public static String showCommandError() {
        return "Cleo: Invalid command!";
    }
    /**
     * Displays an error message when there is an issue loading the UI.
     */
    public void showLoadingError() {
        System.out.println("Error loading ui.");
    }
    /**
     * Displays a line separator to visually divide output in the console.
     */
    public void showLineSeparator() {
        System.out.println("____________________________________________________________");
    }
    /**
     * The main method for testing the Ui class. Currently, it just instantiates an object of the Ui class.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
    }
}
