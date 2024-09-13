package cleo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        // We'll use the concrete ToDos class for testing
        task = new ToDos("Finish homework", "P3");
    }
    @Test
    public void testGetDescription() {
        assertEquals("Finish homework", task.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("[ ] ", task.getStatusIcon());
        task.setDone();
        assertEquals("[X] ", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        assertFalse(task.isDoneString());

        task.setDone();
        assertTrue(task.isDoneString());
    }

    @Test
    public void testUnmarkAsDone() {
        task.setDone();
        assertTrue(task.isDoneString());

        task.setUndone();
        assertFalse(task.isDoneString());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [ ] Finish homework", task.toString());

        task.setDone();
        assertEquals("[T] [X] Finish homework", task.toString());
    }
}
