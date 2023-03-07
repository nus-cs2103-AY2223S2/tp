package seedu.vms.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";

    private static final String WHITESPACE = " \t\r\n";

    private static final LocalDateTime EXPECTED_DATE = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime EXPECTED_DATE_ONLY = LocalDateTime.of(2023, 3, 5, 0, 0);

    private static final String VALID_DEFAULT_DATE = "2023-03-05T04:55";
    private static final String VALID_FULL_DATE = "2023-03-05 0455";
    private static final String VALID_DATE_ONLY = "2023-03-05";

    private static final String INVALID_DATE_RUBBISH = "RuBbisH";
    private static final String INVALID_DATE_MIN_OUT = "2023-03-05T04:99";
    private static final String INVALID_DATE_HRS_OUT = "2023-03-05T99:55";
    private static final String INVALID_DATE_DAY_OUT = "2023-03-99T04:55";
    private static final String INVALID_DATE_MTH_OUT = "2023-99-99T04:55";
    private static final String INVALID_DATE_ONLY_MTH_OUT = "2023-99-99";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX,
                () -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseDate_validDefaultDate_returnDate() throws Exception {
        LocalDateTime actualDate = ParserUtil.parseDate(VALID_DEFAULT_DATE);
        assertEquals(EXPECTED_DATE, actualDate);
    }

    @Test
    public void parseDate_validFullDate_returnDate() throws Exception {
        assertEquals(EXPECTED_DATE, ParserUtil.parseDate(VALID_FULL_DATE));
    }

    @Test
    public void parseDate_validDateOnly_returnDate() throws Exception {
        assertEquals(EXPECTED_DATE_ONLY, ParserUtil.parseDate(VALID_DATE_ONLY));
    }

    @Test
    public void parseDate_rubbishDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_RUBBISH));
    }

    @Test
    public void parseDate_minutesOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_MIN_OUT));
    }

    @Test
    public void parseDate_hoursOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_HRS_OUT));
    }

    @Test
    public void parseDate_dayOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_DAY_OUT));
    }

    @Test
    public void parseDate_monthOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_MTH_OUT));
    }

    @Test
    public void parseDateOnly_monthOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_ONLY_MTH_OUT));
    }
}
