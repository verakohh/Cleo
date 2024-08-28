package cleo.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        // We'll use the concrete ToDos class for testing
        task = new ToDos("Finish homework");
    }

    @Test
    public void testGetDescription() {
        assertEquals("Finish homework", task.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("[ ] ", task.getStatusIcon());

        task.markAsDone();
        assertEquals("[X] ", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        assertFalse(task.isDoneString());

        task.markAsDone();
        assertTrue(task.isDoneString());
    }

    @Test
    public void testUnmarkAsDone() {
        task.markAsDone();
        assertTrue(task.isDoneString());

        task.unmarkAsDone();
        assertFalse(task.isDoneString());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [ ] Finish homework", task.toString());

        task.markAsDone();
        assertEquals("[T] [X] Finish homework", task.toString());
    }
}
