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

        while(true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("bye")) {
                System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                break;
            } else if (userInput.equalsIgnoreCase("hi") || userInput.equalsIgnoreCase("hello")) {
                System.out.println("Cleo: Hi there! How can I help you today?:)");
            }
            else {
                System.out.println(userInput);
            }
        }
        scanner.close();
    }
}
