import java.util.Scanner;
public class Cleo {
    public static void main(String[] args) {
        String logo = """
                  /)/) \s
                 ( •.•)
                 (  - )♡
                """;
        System.out.println("Good day! I’m Cleo, your personal assistant,\n" +
                "What’s on the agenda today?\n"
                + logo);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[100];
        int taskCount = 0;

        while(true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("bye")) {
                System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                break;
            } else if (userInput.equalsIgnoreCase("hi") || userInput.equalsIgnoreCase("hello")) {
                System.out.println("Cleo: Hi there! How can I help you today?:)");
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Cleo: Here are your tasks:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("Cleo: added " + userInput);
            }
        }
        scanner.close();
    }
}
