

# Cleo: Your Intuitive Command-Line Task Assistant

## 🌟 Overview

Cleo is a minimalist, command-line personal assistant application designed to streamline task management through natural language input. Built in **Java**, Cleo focuses on efficiency and user experience by providing a simple, responsive interface for managing all your to-dos, deadlines, and events.

<img width="300" height="600" alt="cleo" src="https://github.com/user-attachments/assets/9f2fe349-bc2f-4de8-86a5-1e2f03ca0716" />

## ✨ Features

Cleo has continuously evolved to offer robust task management capabilities with a focus on speed and organization.

### Core Task Management

| Feature | Command Example | Description |
| :--- | :--- | :--- |
| **ToDos** | `todo Buy groceries` | Quickly add a general task. |
| **Deadlines** | `deadline Submit report /by 2024-11-15` | Add a task with a specific due date. |
| **Events** | `event Project meeting /from 10:00 /to 12:00` | Schedule events with start and end times. |
| **Mark/Unmark** | `mark 2` / `unmark 2` | Track completion status for individual tasks. |
| **Search** | `find book` | Search for tasks using keywords. |

### Advanced Organization (v0.3 & v0.4)

| Feature | Impact & Implementation |
| :--- | :--- |
| **Priority System** | Tasks support **priority levels (P0-P4)** where P0 is the highest priority. Cleo automatically **sorts the task list** by priority, ensuring high-priority items appear at the top. |
| **Batch Operations** | Supports adding and deleting **multiple tasks** at once (e.g., `todo call mom; study` or `delete 1 3 5`). |
| **Command Suggestions** | Enhances user experience by suggesting the closest command when a typo is detected (e.g., suggesting `delete` for `deltee`). |

### 🛠️ Technical Improvements (Code Quality)

  * **Code Refactoring:** Major refactoring of task management methods (like `addTask`, `deleteTask`) to reduce **code duplication** and enhance long-term **maintainability**.
  * **Robust Error Handling:** Optimized code by replacing redundant null assertions with appropriate error handling, ensuring **consistent and clear error messages** for invalid inputs.

-----

## 🚀 Get Started (Installation Guide)

Cleo runs as a JAR file and is designed for the command line.

### Requirements

  * Java Runtime Environment (JRE) installed on your system.

### How to Run

1.  **Download:** Download the latest `Cleo.jar` file from the [Releases page](https://github.com/verakohh/Cleo/releases).
2.  **Open Terminal:** Navigate to the folder where you saved the `.jar` file.
3.  **Execute:** Run the application using the following command:

<!-- end list -->

```bash
java -jar Cleo.jar
```

### Command Reference

| Command | Syntax |
| :--- | :--- |
| **Set Priority** | `priority [task number] #[priority level]` (e.g., `priority 1 #P0`) |
| **List Tasks** | `list` |
| **Delete Tasks** | `delete [task number]` or `delete [num1] [num2]` |
| **View Commands**| `commands` |
| **Exit** | `bye` |

-----

## 💡 Future Plans (v0.5)

The next major release is planned to focus on deeper time management features:

  * **Recurring Tasks:** Implementing support for tasks that repeat on a regular schedule.
  * **Deadline Automation:** Enhancing the priority system to automatically adjust task priorities based on upcoming deadlines.
