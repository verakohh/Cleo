import java.util.ArrayList;
import java.util.Scanner;

public class Cleo {

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
    private static CommandType parseCommand(String userInput) {
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

    public static void main(String[] args) throws CleoException {
        String logo = """
                  /)/) \s
                 ( •.•)
                 (  - )♡
                """;
        System.out.println("Good day! I’m Cleo, your personal assistant,\n" +
                "What’s on the agenda today?\n" + logo);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("You:");
            String userInput = scanner.nextLine();
            CommandType command = parseCommand(userInput);

            try {
                switch (command) {
                    case BYE -> {
                        System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                        return;
                    }
                    case HI -> {
                        System.out.println("Cleo: Hi there! How can I help you today?:)");
                    }
                    case LIST -> listTasks(tasks);
                    case MARK -> {
                        int taskNumber = parseTaskNumber(userInput.substring(5), tasks.size());
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("Cleo: Great job! I've marked this task as done:");
                        System.out.println("    " + tasks.get(taskNumber));
                    }
                    case UNMARK -> {
                        int taskNumber = parseTaskNumber(userInput.substring(7), tasks.size());
                        tasks.get(taskNumber).unmarkAsDone();
                        System.out.println("Cleo: Okay! I've unmarked this task as not done yet:");
                        System.out.println("    " + tasks.get(taskNumber));
                    }
                    case DELETE -> {
                        int taskNumber = parseTaskNumber(userInput.substring(7), tasks.size());
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println("Cleo: Noted, I've removed this task:");
                        System.out.println("    " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    case TODO -> addTodoTask(userInput.substring(4).trim(), tasks);
                    case DEADLINE -> addDeadlineTask(userInput.substring(8).trim(), tasks);
                    case EVENT -> addEventTask(userInput.substring(5).trim(), tasks);
                    case INVALID -> System.out.println("Cleo: Invalid command!");
                }
            } catch (CleoException e) {
                System.out.println("Cleo: " + e.getMessage());
            }
        }
    }


    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Cleo: No tasks yet!");
        } else {
            System.out.println("Cleo: Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static int parseTaskNumber(String userInput, int taskListSize) throws CleoException {
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

    private static void addTodoTask(String input, ArrayList<Task> tasks) throws CleoException {
        try {
            if (input.isEmpty()) {
                throw new CleoException("Oops! The description of a todo cannot be empty.");
            }
            tasks.add(new ToDos(input));
            System.out.println("Cleo: Got it. I have added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (CleoException e) {
            throw new CleoException("Please enter a todo description!");
        }
    }

    private static void addDeadlineTask(String input, ArrayList<Task> tasks) throws CleoException {
        try {
            String[] parts = input.split("/by", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CleoException("Oops! The deadline description or date cannot be empty!");
            }
            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
            System.out.println("Cleo: Got it. I have added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (CleoException e) {
            throw new CleoException("Oops! The description of a deadline cannot be empty.");
        }
    }

    private static void addEventTask(String input, ArrayList<Task> tasks) throws CleoException {
        try {
            String[] parts = input.split("/from", 2);

            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            String[] time = parts[1].split("/to", 2);
            if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            tasks.add(new Events(parts[0].trim(), time[0].trim(), time[1].trim()));
            System.out.println("Cleo: Got it. I have added this event:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (CleoException e) {
            throw new CleoException("Oops! The description of an event cannot be empty.");
        }
    }

}
