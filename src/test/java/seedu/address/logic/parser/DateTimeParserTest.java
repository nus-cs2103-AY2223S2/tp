package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class DateTimeParserTest {
    private static final String templateDateTimeString = "12-02-2023 18:00";
    private final LocalDateTime template = LocalDateTime.of(2023, 02, 12, 18, 0);

    @Test
    public void createDateTime_validFormat() throws ParseException {
        LocalDateTime dateTime = DateTimeParser.parseDateTime("12-02-2023 18:00");
        assertEquals(template, dateTime);
    }

    @Test
    public void createDateTime_invalidFormat_parseExceptionThrown() {
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTime("2020-02-12 18:00"));
    }

    @Test
    public void createDateTime_emptyString_nullDateTimeField() throws ParseException {
        LocalDateTime dateTime = DateTimeParser.parseDateTime("");
        assertEquals(dateTime, null);
    }

    @Test void dateTimeFormatter_formatsDateTimeInValidFormat() {
        String formattedDateTime = DateTimeParser.datetimeFormatter(template);
        assertEquals(formattedDateTime, templateDateTimeString);
    }
}
