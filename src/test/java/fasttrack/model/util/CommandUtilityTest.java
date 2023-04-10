package fasttrack.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CommandUtilityTest {

    @Test
    void testParseDateFromUserInput_validFormats_success() {
        assertEquals(LocalDate.of(2023, 3, 1), CommandUtility.parseDateFromUserInput("1/3/23"));
        assertEquals(LocalDate.of(2023, 6, 5), CommandUtility.parseDateFromUserInput("5/6/2023"));
        assertEquals(LocalDate.of(2022, 7, 16), CommandUtility.parseDateFromUserInput("16/7/22"));
        assertEquals(LocalDate.of(2021, 12, 31), CommandUtility.parseDateFromUserInput("31/12/2021"));
    }
    @Test
    void testParseDateFromUserInput_invalidFormats_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> CommandUtility.parseDateFromUserInput("32/2/2022"));
        assertThrows(IllegalArgumentException.class, () -> CommandUtility.parseDateFromUserInput("12/13/2023"));
        assertThrows(IllegalArgumentException.class, () -> CommandUtility.parseDateFromUserInput("12w/12/23a"));
        assertThrows(IllegalArgumentException.class, () -> CommandUtility.parseDateFromUserInput("12/c12/23a"));
        assertThrows(IllegalArgumentException.class, () -> CommandUtility.parseDateFromUserInput("2023-4-31"));
    }

}
