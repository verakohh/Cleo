package cleo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseCommand_todo() {
        assertEquals(Parser.CommandType.TODO, Parser.parseCommand("todo read book"));
    }

    @Test
    public void testParseCommand_deadline() {
        assertEquals(Parser.CommandType.DEADLINE, Parser.parseCommand("deadline submit report /by 2024-12-01 23:59"));
    }

    @Test
    public void testParseCommand_event() {
        assertEquals(Parser.CommandType.EVENT, Parser.parseCommand("event team meeting /from 2024-12-01 14:00 "
                + "/to 2024-12-01 16:00"));
    }

    @Test
    public void testParseCommand_list() {
        assertEquals(Parser.CommandType.LIST, Parser.parseCommand("list"));
    }

    @Test
    public void testParseCommand_mark() {
        assertEquals(Parser.CommandType.MARK, Parser.parseCommand("mark 1"));
    }

    @Test
    public void testParseCommand_unmark() {
        assertEquals(Parser.CommandType.UNMARK, Parser.parseCommand("unmark 1"));
    }

    @Test
    public void testParseCommand_delete() {
        assertEquals(Parser.CommandType.DELETE, Parser.parseCommand("delete 1"));
    }

    @Test
    public void testParseCommand_bye() {
        assertEquals(Parser.CommandType.BYE, Parser.parseCommand("bye"));
    }

    @Test
    public void testParseCommand_hi() {
        assertEquals(Parser.CommandType.HI, Parser.parseCommand("hi"));
        assertEquals(Parser.CommandType.HI, Parser.parseCommand("hello"));
    }

    @Test
    public void testParseCommand_invalid() {
        assertEquals(Parser.CommandType.INVALID, Parser.parseCommand("random text"));
    }

    @Test
    public void testParseTaskNumber_valid() throws CleoException {
        assertEquals(0, Parser.parseTaskNumber("1", 5));
    }

    @Test
    public void testParseTaskNumber_outOfBounds() {
        CleoException exception = assertThrows(CleoException.class, () -> Parser.parseTaskNumber("6", 5));
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void testParseTaskNumber_invalidNumberFormat() {
        CleoException exception = assertThrows(CleoException.class, () -> Parser.parseTaskNumber("abc", 5));
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }
}
