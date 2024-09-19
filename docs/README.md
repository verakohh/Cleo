
# Cleo User Guide

![Ui.png](./Ui.png)

## Introduction to Cleo

Cleo is your friendly, interactive personal assistant chatbot designed to help you keep track of tasks, deadlines, and
events. With an intuitive GUI, Cleo is designed to be responsive and easy to use. Whether you're managing daily tasks
or planning future events, Cleo is here to assist you!

---

## Table of Contents
- [Features](#features)
  - [Viewing help: `commands`](#viewing-help-commands)
  - [Adding a task: `todo`](#adding-a-task-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Listing tasks: `list`](#listing-tasks-list)
  - [Marking task: `mark`](#marking-tasks-mark)
  - [Unmarking task: `unmark`](#unmarking-tasks-unmark)
  - [Deleting tasks: `delete`](#deleting-tasks-delete)
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Changing task priority: `priority`](#changing-task-priority-priority)
  - [Exiting the application: `bye`](#exiting-the-application-bye)
- [Command Summary](#command-summary)

---

## Features

### Viewing list of commands: <span style="color:#9Ec9ef">commands</span>

Shows a message explaining how to access the commands page.

**Format**: `commands`

**Expected Outcome**:
```
Cleo: Here's a list of commands you can use:
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
  9. PRIORITY [task number] [#priority level] - Changes priority level of the task. Choose from P[0-4]
     (e.g. priority 2 #P0)
  10. BYE - Exits the application.
```

---

### Adding a task: <span style="color:#9Ec9ef">todo</span>

Adds a todo task to Cleo's task list.

**Format**: `todo [task description]`  
**Example**: `todo call mom #P2`

You can add multiple todo tasks at once by separating them with a semicolon (e.g., `todo call;bathe;study`).

**Expected Outcome**:
```
Cleo: Added todo task(s)!
[T] [ ] call mom (Priority: P2)
[T] [ ] bathe (Priority: P4)
[T] [ ] study (Priority: P4)
```

---

### Adding a deadline: <span style="color:#9Ec9ef">deadline</span>

Adds a deadline task to Cleo's task list.

**Format**: `deadline [task description] /by [date]`  
**Example**: `deadline submit report /by 2025-01-01 11:00`

**Expected Outcome**:
```
Cleo: Added deadline task(s)!
[D] [ ] submit report (by: Jan 1 2025 11:00 AM) (Priority: P4)
```

---

### Adding an event: <span style="color:#9Ec9ef">event</span>

Adds an event task to Cleo's task list.

**Format**: `event [task description] /from [start time] /to [end time]`  
**Example**: `event team meeting /from 2025-01-01 09:00 /to 2025-01-01 11:00`

**Expected Outcome**:
```
Cleo: Added event task(s)!
[E] [ ] team meeting (from: Jan 1 2025 09:00 AM to: Jan 1 2025 11:00 AM) (Priority: P4)
```

---

### Changing task priority: <span style="color:#9Ec9ef">priority</span>

Cleo allows you to assign priority levels to your tasks (P0 to P4, where P0 is the highest priority and P4 the lowest).

**Format**: `priority [task number] #[priority level]`  
**Example**: `priority 2 #P1`

**Expected Outcome**:
```
Cleo: Updated task priority!
[T] [ ] buy groceries (Priority: P1)
```

---

### Listing Tasks: <span style="color:#9Ec9ef">list</span>

You can view all your tasks using the `list` command, which shows all tasks with their completion status and priority 
level.

**Format**: `list`

**Expected Outcome**:
```
Here are your tasks:
1. [T] [ ] buy groceries (Priority: P1)
2. [D] [ ] submit assignment (by: Dec 25 2024 11:59 PM) (Priority: P2)
```

---

### Marking Tasks: <span style="color:#9Ec9ef">mark</span>

To mark a task as completed, use the `mark` command.

**Format**: `mark [task number]`  
**Example**: `mark 1`

**Expected Outcome**:
```
Cleo: Great job! I've marked this task as done:
[T] [X] buy groceries (Priority: P1)
```

---

### Unmarking Tasks: <span style="color:#9Ec9ef">unmark</span>

To unmark a task as not done, use the `unmark` command.

**Format**: `unmark [task number]`  
**Example**: `unmark 1`

**Expected Outcome**:
```
Cleo: Okay! I've unmarked this task as not done yet:
[T] [ ] buy groceries (Priority: P1)
```

---

### Deleting Tasks: <span style="color:#9Ec9ef">delete</span>

You can delete tasks using the `delete` command.

**Format**: `delete [task number]`  
**Example**: `delete 1`

**Expected Outcome**:
```
Cleo: Noted, I've removed this task:
[T] [ ] buy groceries (Priority: P1)
```

---

### Finding Tasks: <span style="color:#9Ec9ef">find</span>

Use the `find` command to search for tasks containing specific keywords.

**Format**: `find [keyword]`  
**Example**: `find assignment`

**Expected Outcome**:
```
Cleo: Here are the matching tasks in your list:
[D] [ ] submit assignment (by: Dec 25 2024 11:59 PM) (Priority: P2)
```

---
### Exiting the Application: <span style="color:#9Ec9ef">bye</span>


To exit Cleo, simply use the `bye` command.

**Format**: `bye`

**Expected Outcome**:
```
Cleo: Goodbye, hope to see you again soon!
```

---

## Command Summary

| Command   | Format                               | Example                              |
|-----------|--------------------------------------|--------------------------------------|
| todo      | `todo [description]`                 | `todo buy groceries #P2`             |
| deadline  | `deadline [description] /by [date]`  | `deadline submit report /by 2025-01-01 11:00` |
| event     | `event [description] /from [start] /to [end]` | `event meeting /from 2025-01-01 09:00 /to 2025-01-01 11:00` |
| list      | `list`                               | `list`                               |
| mark      | `mark [task number]`                 | `mark 1`                             |
| unmark    | `unmark [task number]`               | `unmark 1`                           |
| delete    | `delete [task number]`               | `delete 1`                           |
| find      | `find [keyword]`                     | `find assignment`                    |
| priority  | `priority [task number] #[priority]` | `priority 1 #P0`                     |
| bye       | `bye`                                | `bye`                                |


---
## Acknowledgements
I would like to extend my thanks to ChatGPT by OpenAI for assisting with the writing of Javadocs and helping design parts of the JavaFX code. ChatGPT provided valuable suggestions and explanations that significantly improved the structure and readability of the code and documentation.
