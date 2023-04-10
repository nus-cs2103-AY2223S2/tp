package fasttrack.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class StorageUtilityTest {

    @Test
    public void testParseDateFromJson_validDate() {
        LocalDate expectedDate = LocalDate.of(2023, 4, 30);
        LocalDate parsedDate = StorageUtility.parseDateFromJson("2023-04-30");
        assertEquals(expectedDate, parsedDate);
    }

    @Test
    public void testParseDateFromJson_invalidDate() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            StorageUtility.parseDateFromJson("20-4-31");
        });
    }
}
