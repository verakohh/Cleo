import java.io.PrintStream;
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

        Task[] tasks = new Task[100];
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
                //LIST OF TASKS
                if (taskCount == 0) {
                    System.out.println("No tasks yet!");
                } else {
                    System.out.println("Cleo: Here are your tasks:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                }
            } else if (userInput.startsWith("mark ")) {
                //MARK AS DONE
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("Cleo: Great job! I've marked this task as done:");
                    System.out.println("    " + tasks[taskNumber]);
                }
            } else if (userInput.startsWith("unmark ")) {
                //UNMARK AS DONE
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].unmarkAsDone();
                    System.out.println("Cleo: Okay! I've unmarked this task as not done yet:");
                    System.out.println("    " + tasks[taskNumber]);
                }
            } else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("Cleo: added " + userInput);
            }
        }
        scanner.close();
    }
}

