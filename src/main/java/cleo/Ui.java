package cleo;

/**
 * Represents the user interface of the Cleo chatbot.
 * Provides methods to display messages and separators in the console.
 */
public class Ui {
    /**
     * Displays the welcome message when the Cleo chatbot is started.
     * Includes a simple ASCII art of a cute character to greet the user.
     */
    public String displayWelcomeMessage() {
        String logo = """
                  /)/) \s
                 ( •.•)
                 (  - )♡
                """;
        String greeting = "Good day! I’m Cleo, your personal assistant,\n"
                + "What’s on the agenda today?\n";
        return (greeting + logo);
    }

    public void showLoadingError() {
        System.out.println("Error loading ui.");
    }

    public void showLineSeparator() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.displayWelcomeMessage();

    }
}
