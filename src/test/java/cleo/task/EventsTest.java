package cleo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventsTest {

    @Test
    public void testValidEventCreation() {
        Events event = new Events("Project Meeting", "2024-12-01 14:00", "2024-12-01 16:00");
        assertEquals("Project Meeting", event.getDescription());
        assertEquals("Dec 01 2024 02:00 pm", event.getFrom());
        assertEquals("Dec 01 2024 04:00 pm", event.getTo());
    }

    @Test
    public void testEventWithPastStartDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Events("Project Meeting", "2022-12-01 14:00", "2024-12-01 16:00"));
        assertEquals("The date(s) cannot be in the past!", exception.getMessage());
    }

    @Test
    public void testEventWithPastEndDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Events("Project Meeting", "2024-12-01 14:00", "2022-12-01 16:00"));
        assertEquals("The date(s) cannot be in the past!", exception.getMessage());
    }

    @Test
    public void testEventWithEndBeforeStart() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Events("Project Meeting", "2024-12-01 16:00", "2024-12-01 14:00"));
        assertEquals("The end time of the event should be later than the start time!", exception.getMessage());
    }

    @Test
    public void testInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Events("Project Meeting", "2024-12-01", "2024-12-01 16:00"));
        assertEquals("Invalid date and time format! Please use the format 'yyyy-MM-dd HH:mm'.", exception.getMessage());
    }

    @Test
    public void testToString() {
        Events event = new Events("Project Meeting", "2024-12-01 14:00", "2024-12-01 16:00");
        assertEquals("[E] [ ] Project Meeting (from: Dec 01 2024 02:00 pm to: Dec 01 2024 04:00 pm)", event.toString());
    }
}
