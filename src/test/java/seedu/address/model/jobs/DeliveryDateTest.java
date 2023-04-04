package seedu.address.model.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeliveryDateTest {
    @Test
    void testIsValidDate() {
        // invalid day
        assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryDate("2023-05-0");
        });

        // invalid year
        assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryDate("22023-05-01");
        });

        // invalid month
        assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryDate("22023-15-01");
        });

        // invalid date
        assertThrows(IllegalArgumentException.class, () -> {
            new DeliveryDate("2023-02-40");
        });
    }

    @Test
    void testToString() {
        assertEquals(LocalDate.parse("2023-05-01").format(DateTimeFormatter.ofPattern(("yyyy-MM-dd"))),
                new DeliveryDate("2023-05-01").toString());
    }
}
