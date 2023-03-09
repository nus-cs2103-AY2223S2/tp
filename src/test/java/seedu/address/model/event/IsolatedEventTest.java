package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class IsolatedEventTest {

    @Test
    void occurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", "09/03/2023 14:00",
                "09/03/2023 15:00");
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime startPeriod = LocalDateTime.parse("09/03/2023 14:00", newFormatter);
        LocalDateTime endPeriod = LocalDateTime.parse("09/03/2023 15:00", newFormatter);
        assertEquals(true, event.occursBetween(startPeriod, endPeriod));
    }

    @Test
    void notOccurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", "09/03/2023 14:00",
                "09/03/2023 15:00");
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime startPeriod = LocalDateTime.parse("09/03/2023 16:00", newFormatter);
        LocalDateTime endPeriod = LocalDateTime.parse("09/03/2023 18:00", newFormatter);
        assertEquals(false, event.occursBetween(startPeriod, endPeriod));
    }

    @Test
    void toString_test() {
        IsolatedEvent event = new IsolatedEvent("biking", "09/03/2023 14:00",
                "09/03/2023 15:00");
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        assertEquals("biking from: 09/03/2023 14:00 to: 09/03/2023 15:00", event.toString());
    }

}
