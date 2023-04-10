package expresslibrary.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    public void testFormatDate() {
        LocalDate date = LocalDate.of(2023, 3, 18);
        String expected = "18/03/2023";
        String actual = DateUtil.formatDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseValidDate() {
        String dateString = "18/03/2023";
        LocalDate expected = LocalDate.of(2023, 3, 18);
        LocalDate actual = DateUtil.parseDate(dateString);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseInvalidDate() {
        String dateString = "18/03/23";
        assertThrows(DateTimeParseException.class, () -> DateUtil.parseDate(dateString));
    }

    @Test
    public void testParseNullDate() {
        String dateString = null;
        assertThrows(NullPointerException.class, () -> DateUtil.parseDate(dateString));
    }

}
