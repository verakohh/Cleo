# Cleo User Guide

![Ui.png](./Ui.png)

## Introduction to Cleo

Cleo is your friendly, interactive personal assistant chatbot designed to help you keep track of tasks, deadlines, and events. With an intuitive command-line interface, Cleo is designed to be responsive and easy to use. Whether you're managing daily tasks or planning future events, Cleo is here to assist you!

---

## Adding Deadlines

Cleo helps you manage important deadlines with ease. You can add a deadline task by using the `deadline` command.

### Usage:
``` 
deadline [task description] /by [date] 
```

### Example:
```
deadline submit assignment /by 2024-12-25 23:59
```

### Expected Outcome:
Cleo will add a deadline task and confirm the addition:
```
Cleo: Added deadline task!
[D] [ ] submit assignment (by: Dec 25 2024 11:59 PM)
```

---

## Adding Events

To keep track of events with start and end times, use the `event` command.

### Usage:
``` 
event [task description] /from [start time] /to [end time]
```

### Example:
```
event project meeting /from 2024-12-20 09:00 /to 2024-12-20 11:00
```

### Expected Outcome:
Cleo will add the event and confirm the addition:
```
Cleo: Added event task!
[E] [ ] project meeting (from: Dec 20 2024 09:00 AM to: Dec 20 2024 11:00 AM)
```

---

## Adding To-Do Tasks

You can quickly add a basic to-do task using the `todo` command.

### Usage:
``` 
todo [task description]
```

### Example:
```
todo buy groceries
```

### Expected Outcome:
Cleo will add a to-do task:
```
Cleo: Added todo task!
[T] [ ] buy groceries
```

---

## Prioritizing Tasks

Cleo allows you to assign priority levels to your tasks (P0 to P4, where P0 is the highest priority and P4 the lowest).

### Usage:
``` 
priority [task number] #[priority level]
```

### Example:
```
priority 2 #P1
```

### Expected Outcome:
Cleo will update the priority of the specified task:
```
Cleo: Updated task priority!
[T] [ ] buy groceries (Priority: P1)
```

---

## Listing Tasks

You can view all your tasks using the `list` command, which shows all tasks with their completion status and priority level.

### Usage:
``` 
list
```

### Expected Outcome:
Cleo will display all the tasks in the list:
```
Here are your tasks:
1. [T] [ ] buy groceries (Priority: P1)
2. [D] [ ] submit assignment (by: Dec 25 2024 11:59 PM) (Priority: P2)
```

---

## Marking and Unmarking Tasks

To mark a task as completed, use the `mark` command.

### Usage:
``` 
mark [task number]
```

### Example:
```
mark 1
```

### Expected Outcome:
Cleo will mark the task as done:
```
Cleo: Great job! I've marked this task as done:
[T] [X] buy groceries (Priority: P1)
```

To unmark a task as not done, use the `unmark` command.

### Usage:
``` 
unmark [task number]
```

### Example:
```
unmark 1
```

### Expected Outcome:
```
Cleo: Okay! I've unmarked this task as not done yet:
[T] [ ] buy groceries (Priority: P1)
```

---

## Deleting Tasks

You can delete tasks using the `delete` command.

### Usage:
``` 
delete [task number]
```

### Example:
```
delete 1
```

### Expected Outcome:
Cleo will delete the specified task:
```
Cleo: Noted, I've removed this task:
[T] [ ] buy groceries (Priority: P1)
```

---

## Finding Tasks

Use the `find` command to search for tasks containing specific keywords.

### Usage:
``` 
find [keyword]
```

### Example:
```
find assignment
```

### Expected Outcome:
```
Cleo: Here are the matching tasks in your list:
[D] [ ] submit assignment (by: Dec 25 2024 11:59 PM) (Priority: P2)
```

---

## Command Suggestions

If you make a typo when entering a command, Cleo will try to help by suggesting the closest valid command.

### Example:
```
deltee 1
```

### Expected Outcome:
```
Cleo: Did you mean 'delete'?
```

---

## Exiting the Application

To exit Cleo, simply use the `bye` command.

### Usage:
``` 
bye
```

### Expected Outcome:
Cleo will say goodbye and exit the application:
```
Cleo: Goodbye, hope to see you again soon!
```

---

## Conclusion

Cleo is here to help you organize your tasks efficiently, with priority management, event tracking, and deadline management. The simple command interface makes it easy to stay on top of your daily and long-term tasks. Let Cleo assist you in keeping your schedule organized and productive!
