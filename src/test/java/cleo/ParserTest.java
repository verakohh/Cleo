package cleo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
public class ParserTest {

    @Test
    public void parseCommand_validCommands_success() {
        assertEquals(Parser.CommandType.TODO, Parser.parseCommand("todo read book"));
        assertEquals(Parser.CommandType.DEADLINE, Parser.parseCommand("deadline submit report "
                + "/by 2021-12-31"));
        assertEquals(Parser.CommandType.EVENT, Parser.parseCommand("event project meeting "
                + "/from 2021-12-01 /to 2021-12-02"));
        assertEquals(Parser.CommandType.LIST, Parser.parseCommand("list"));
        assertEquals(Parser.CommandType.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Parser.CommandType.UNMARK, Parser.parseCommand("unmark 1"));
        assertEquals(Parser.CommandType.DELETE, Parser.parseCommand("delete 1"));
        assertEquals(Parser.CommandType.FIND, Parser.parseCommand("find book"));
        assertEquals(Parser.CommandType.PRIORITY, Parser.parseCommand("priority 1 #P0"));
        assertEquals(Parser.CommandType.BYE, Parser.parseCommand("bye"));
        assertEquals(Parser.CommandType.HI, Parser.parseCommand("hi"));
        assertEquals(Parser.CommandType.COMMANDS, Parser.parseCommand("commands"));
    }

    @Test
    public void parseCommand_invalidCommand_returnsInvalid() {
        assertEquals(Parser.CommandType.INVALID, Parser.parseCommand("unknowncommand"));
    }
    @Test
    public void parseTaskNumber_validNumber_success() throws CleoException {
        int taskListSize = 5;
        assertEquals(0, Parser.parseTaskNumber("1", taskListSize));
        assertEquals(4, Parser.parseTaskNumber("5", taskListSize));
    }

    @Test
    public void parseTaskNumber_invalidNumber_throwsException() {
        int taskListSize = 5;
        Exception exception = assertThrows(CleoException.class, () -> {
            Parser.parseTaskNumber("0", taskListSize);
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());

        exception = assertThrows(CleoException.class, () -> {
            Parser.parseTaskNumber("6", taskListSize);
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());

        exception = assertThrows(CleoException.class, () -> {
            Parser.parseTaskNumber("abc", taskListSize);
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }
    @Test
    public void parsePriority_validPriority_success() throws CleoException {
        assertEquals("P0", Parser.parsePriority("P0"));
        assertEquals("P1", Parser.parsePriority("P1"));
        assertEquals("P2", Parser.parsePriority("P2"));
        assertEquals("P3", Parser.parsePriority("P3"));
        assertEquals("P4", Parser.parsePriority("P4"));
    }

    @Test
    public void parsePriority_invalidPriority_throwsException() {
        Exception exception = assertThrows(CleoException.class, () -> {
            Parser.parsePriority("P5");
        });
        assertEquals("Invalid priority level! Priority must be between P0 and P4.", exception.getMessage());

        exception = assertThrows(CleoException.class, () -> {
            Parser.parsePriority("invalid");
        });
        assertEquals("Invalid priority level! Priority must be between P0 and P4.", exception.getMessage());
    }
    @Test
    public void parseTodoInput_validInput_success() throws CleoException {
        Pair<String, String> result = Parser.parseTodoInput("read book");
        assertEquals("read book", result.getKey());
        assertNull(result.getValue());

        result = Parser.parseTodoInput("read book #P2");
        assertEquals("read book", result.getKey());
        assertEquals("P2", result.getValue());
    }

    @Test
    public void parseTodoInput_emptyDescription_throwsException() {
        Exception exception = assertThrows(CleoException.class, () -> {
            Parser.parseTodoInput("");
        });
        assertEquals("Oops! The description of a todo cannot be empty!", exception.getMessage());
    }
    @Test
    public void parseDeadlineInput_validInput_success() throws CleoException {
        String[] result = Parser.parseDeadlineInput("submit report /by 2021-12-31");
        assertEquals("submit report", result[0]);
        assertEquals("2021-12-31", result[1]);
        assertNull(result[2]);

        result = Parser.parseDeadlineInput("submit report /by 2021-12-31 #P1");
        assertEquals("submit report", result[0]);
        assertEquals("2021-12-31", result[1]);
        assertEquals("P1", result[2]);
    }

    @Test
    public void parseDeadlineInput_missingBy_throwsException() {
        Exception exception = assertThrows(CleoException.class, () -> {
            Parser.parseDeadlineInput("submit report");
        });
        assertEquals("Oops! The deadline description or date cannot be empty!", exception.getMessage());
    }
    @Test
    public void parseEventInput_validInput_success() throws CleoException {
        String[] result = Parser.parseEventInput("project meeting /from 2021-12-01 /to 2021-12-02");
        assertEquals("project meeting", result[0]);
        assertEquals("2021-12-01", result[1]);
        assertEquals("2021-12-02", result[2]);
        assertNull(result[3]);

        result = Parser.parseEventInput("project meeting /from 2021-12-01 /to 2021-12-02 #P3");
        assertEquals("project meeting", result[0]);
        assertEquals("2021-12-01", result[1]);
        assertEquals("2021-12-02", result[2]);
        assertEquals("P3", result[3]);
    }

    @Test
    public void parseEventInput_missingFromOrTo_throwsException() {
        Exception exception = assertThrows(CleoException.class, () -> {
            Parser.parseEventInput("project meeting");
        });
        assertEquals("Oops! The event description or date cannot be empty!", exception.getMessage());

        exception = assertThrows(CleoException.class, () -> {
            Parser.parseEventInput("project meeting /from 2021-12-01");
        });
        assertEquals("Oops! Please specify both start and end times for the event!", exception.getMessage());
    }





}
