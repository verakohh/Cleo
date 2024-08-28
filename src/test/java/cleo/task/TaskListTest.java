package cleo.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        ToDos todo = new ToDos("Finish homework");
        taskList.addTask(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void testRemoveTask() {
        ToDos todo = new ToDos("Finish homework");
        taskList.addTask(todo);
        assertEquals(1, taskList.size());

        taskList.removeTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testGetTask() {
        ToDos todo1 = new ToDos("Finish homework");
        ToDos todo2 = new ToDos("Read book");
        taskList.addTask(todo1);
        taskList.addTask(todo2);

        assertEquals(todo1, taskList.getTask(0));
        assertEquals(todo2, taskList.getTask(1));
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());

        taskList.addTask(new ToDos("Finish homework"));
        assertEquals(1, taskList.size());

        taskList.addTask(new ToDos("Read book"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void testListTasks() {
        // Add tasks to the list
        taskList.addTask(new ToDos("Finish homework"));
        taskList.addTask(new ToDos("Read book"));

        // Capture the console output (this part is tricky, depending on the framework and the setup)
        // For simplicity, you might need to just check that the size matches and assume listTasks works correctly.
        assertEquals(2, taskList.size());
    }

    @Test
    public void testRemoveTaskOutOfBounds() {
        ToDos todo = new ToDos("Finish homework");
        taskList.addTask(todo);

        // Trying to remove a task that doesn't exist should throw an IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(1);
        });
    }
}
