package cleo;
public class Ui {
    public void displayWelcomeMessage() {
        String logo = """
                  /)/) \s
                 ( •.•)
                 (  - )♡
                """;
        System.out.println("Good day! I’m Cleo, your personal assistant,\n" +
                "What’s on the agenda today?\n" + logo);
        showLineSeparator();
    }

    public static void showLoadingError() {
        System.out.println("Error loading ui.");
    }

    public static void showLineSeparator() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.displayWelcomeMessage();

    }
}
