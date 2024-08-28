package cleo;

import cleo.task.*;

import java.util.Scanner;
import java.io.IOException;



public class Cleo {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    public Cleo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    public void run() {
        ui.displayWelcomeMessage();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("You:");
                String userInput = scanner.nextLine();
                Parser.CommandType command = Parser.parseCommand(userInput);
                ui.showLineSeparator();

                try {
                    switch (command) {
                    case BYE -> {
                        System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                        return;
                    }
                    case HI -> System.out.println("Cleo: Hi there! How can I help you today?:)");
                    case LIST -> tasks.listTasks();
                    case MARK -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(5), tasks.size());
                        tasks.getTask(taskNumber).setDone();
                        System.out.println("Cleo: Great job! I've marked this task as done:");
                        System.out.println("    " + tasks.getTask(taskNumber));
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case UNMARK -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(7), tasks.size());
                        tasks.getTask(taskNumber).setUndone();
                        System.out.println("Cleo: Okay! I've unmarked this task as not done yet:");
                        System.out.println("    " + tasks.getTask(taskNumber));
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case DELETE -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(7), tasks.size());
                        tasks.removeTask(taskNumber);
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case TODO -> {
                        addTodoTask(userInput.substring(4).trim());
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case DEADLINE -> {
                        addDeadlineTask(userInput.substring(8).trim());
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case EVENT -> {
                        addEventTask(userInput.substring(5).trim());
                        storage.saveTasks(tasks);  // Save tasks to file
                    }
                    case INVALID -> System.out.println("Cleo: Invalid command!");
                    }
                } catch (CleoException e) {
                    System.out.println("Cleo: " + e.getMessage());
                }
            }
        }
    }

    private void addTodoTask(String input) throws CleoException {
        try {
            if (input.isEmpty()) {
                throw new CleoException("Oops! The description of a todo cannot be empty.");
            }
            tasks.addTask(new ToDos(input));
        } catch (CleoException e) {
            throw new CleoException("Please enter a todo description!");
        }
    }

    private void addDeadlineTask(String input) throws CleoException {
        try {
            String[] parts = input.split("/by", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CleoException("Oops! The deadline description or date cannot be empty!");
            }
            tasks.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
        }  catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
    }


    private void addEventTask(String input) throws CleoException {
        try {
            String[] parts = input.split("/from", 2);

            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            String[] time = parts[1].split("/to", 2);
            if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            tasks.addTask(new Events(parts[0].trim(), time[0].trim(), time[1].trim()));
        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
    }
    public static void main(String[] args) throws CleoException {
        new Cleo("./data/cleo.txt").run();
    }

}
