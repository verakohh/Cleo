import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Cleo {
    public static void main(String[] args) throws CleoException {
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

        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;

        while(true) {
            System.out.print("You: ");
            userInput = scanner.nextLine();
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                    break;
                } else if (userInput.equalsIgnoreCase("hi") || userInput.equalsIgnoreCase("hello")) {
                    System.out.println("Cleo: Hi there! How can I help you today?:)");
                } else if (userInput.equalsIgnoreCase("list")) {
                    //LIST OF TASKS
                    if (tasks.isEmpty()) {
                        System.out.println("Cleo: No tasks yet!");
                    } else {
                        System.out.println("Cleo: Here are your tasks:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (userInput.startsWith("mark ")) {
                    //MARK AS DONE
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                        if (taskNumber >= 0 && taskNumber < taskCount) {
                            tasks.get(taskNumber).markAsDone();
                            System.out.println("Cleo: Great job! I've marked this task as done:");
                            System.out.println("    " + tasks.get(taskNumber));
                        } else {
                            System.out.println("Cleo: Please provide a valid task number.");
                        }
                    } catch(NumberFormatException e) {
                        System.out.println("Cleo: Please provide a valid task number.");
                    }
                } else if (userInput.startsWith("unmark ")) {
                    //UNMARK AS DONE
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        if (taskNumber >= 0 && taskNumber < taskCount) {
                            tasks.get(taskNumber).unmarkAsDone();
                            System.out.println("Cleo: Okay! I've unmarked this task as not done yet:");
                            System.out.println("    " + tasks.get(taskNumber));
                        } else {
                            System.out.println("Cleo: Please provide a valid task number.");
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Cleo: Please provide a valid task number.");
                    }
                } else if (userInput.startsWith("delete ")) {
                    //DELETE TASK
                    try {

                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        if (taskNumber >= 0 && taskNumber < taskCount) {
                            Task removedTask = tasks.remove(taskNumber);
                            System.out.println("Cleo: Noted, I've removed this task:");
                            System.out.println("    " + removedTask);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            taskCount--;
                        } else {
                            if (tasks.size() == 0) {
                                System.out.println("Cleo: No tasks to delete yet.");
                            } else {
                                System.out.println("Cleo: Please provide a valid task number.");
                            }
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Cleo: Please provide a valid task number.");
                    }
                } else if (userInput.startsWith("todo")) {
                    //TODO
                    //ERROR
                    if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
                        throw new CleoException("Oops! The description of a todo cannot be empty.");
                    }
                    String input = userInput.substring(5).trim();
                    tasks.add(new ToDos(input));
                    System.out.println("Cleo: Got it. I have added this task:");
                    System.out.println(tasks.getLast());
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    //DEADLINE
                    //ERROR
                    if (userInput.length() <= 9 || userInput.substring(9).trim().isEmpty()) {
                        throw new CleoException("Oops! The deadline description or date cannot be empty!");
                    }
                    String input = userInput.substring(9).trim();
                    String[] parts = input.split("/by", 2);
                    //ERROR
                    if (parts.length < 2|| parts[1].trim().isEmpty()) {
                        throw new CleoException("Oops! The deadline description or date cannot be empty!");
                    }
                    String desc = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(desc, by));
                    System.out.println("Cleo: Got it. I have added this task:");
                    System.out.println(tasks.getLast());
                    taskCount++;
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    //EVENT
                    //ERROR
                    if (userInput.length() <= 6 || userInput.substring(6).trim().isEmpty()) {
                        throw new CleoException("Oops! The event description or date cannot be empty!");
                    }
                    String input = userInput.substring(6).trim();
                    String[] parts = input.split("/from", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new CleoException("Oops! Please specify both start and end times for the event!");
                    }
                    String desc = parts[0].trim();
                    String[] time = parts[1].split("/to", 2);
                    //ERROR FOR DATE
                    if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                        throw new CleoException("Oops! Please specify both start and end times for the event!");
                    }
                    String from = time[0].trim();
                    String to = time[1].trim();

                    tasks.add(new Events(desc, from, to));
                    System.out.println("Cleo: Got it. I have added this event:");
                    System.out.println(tasks.getLast());
                    taskCount++;
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Cleo: Invalid command!");
                }
            } catch (CleoException e) {
                System.out.println("Cleo: " + e.getMessage());
            }

        }
        scanner.close();
    }
}

