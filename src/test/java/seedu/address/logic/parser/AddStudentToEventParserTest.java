package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentToEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class AddStudentToEventParserTest {
    public static final String VALID_COMMAND_TUTORIAL = "1 Tutorial/1";
    public static final String VALID_COMMAND_LAB = "1 Lab/1";
    public static final String VALID_COMMAND_CONSULTATION = "1 Consultation/1";
    public static final String INVALID_EVENT_FIELD_COMMAND = "1 Leb/1";
    public static final String TOO_MANY_EVENT_FIELDS_COMMAND = "1 Lab/1 Tutorial/1";
    public static final String INVALID_STUDENT_INDEX_COMMAND = "1 1 1 Tutorial/1";
    public static final String INVALID_EVENT_INDEX_COMMAND = "1 Tutorial/1 1 1";
    public static final String EMPTY_EVENT_COMMAND = "1";

    @Test
    void parse_validTutorial_success() throws ParseException {
        assert(new AddStudentToEventParser().parse(VALID_COMMAND_TUTORIAL)
                instanceof AddStudentToEventCommand);
    }

    @Test
    void parse_validLab_success() throws ParseException {
        assert(new AddStudentToEventParser().parse(VALID_COMMAND_LAB)
                instanceof AddStudentToEventCommand);
    }

    @Test
    void parse_validConsultation_success() throws ParseException {
        assert(new AddStudentToEventParser().parse(VALID_COMMAND_CONSULTATION)
                instanceof AddStudentToEventCommand);
    }

    @Test
    void parse_invalidEventField_throwsParseException() {
        assertThrows(ParseException.class, () ->
                new AddStudentToEventParser().parse(INVALID_EVENT_FIELD_COMMAND));
    }

    @Test
    void parse_tooManyEventFields_throwsParseException() {
        assertThrows(ParseException.class, () ->
                new AddStudentToEventParser().parse(TOO_MANY_EVENT_FIELDS_COMMAND));
    }

    @Test
    void parse_invalidStudentIndex_throwsParseException() {
        assertThrows(ParseException.class, () ->
                new AddStudentToEventParser().parse(INVALID_STUDENT_INDEX_COMMAND));
    }

    @Test
    void parse_invalidEventIndex_throwsParseException() {
        assertThrows(ParseException.class, () ->
                new AddStudentToEventParser().parse(INVALID_EVENT_INDEX_COMMAND));
    }

    @Test
    void parse_emptyEvent_throwsParseException() {
        assertThrows(ParseException.class, () ->
                new AddStudentToEventParser().parse(EMPTY_EVENT_COMMAND));
    }
}
