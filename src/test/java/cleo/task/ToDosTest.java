package cleo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void testToDosToString() {
        ToDos todo = new ToDos("Finish assignment");
        String expected = "[T] [ ] Finish assignment";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToDosDescription() {
        ToDos todo = new ToDos("Read book");
        String expected = "Read book";
        assertEquals(expected, todo.getDescription());
    }
}
