package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMETABLE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_PREFIX_TIMETABLE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_ARG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMETABLE_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.timetable.TimetableDateCommand;
import seedu.address.model.jobs.DeliveryDate;

public class TimetableDateCommandParserTest {

    private TimetableDateCommandParser parser = new TimetableDateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, VALID_TIMETABLE_DATE, new TimetableDateCommand(VALID_DATE_ARG));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableDateCommand.MESSAGE_USAGE);

        // missing date prefix
        assertParseFailure(parser, MISSING_PREFIX_TIMETABLE_DATE, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_TIMETABLE_DATE, DeliveryDate.MESSAGE_CONSTRAINTS);
    }
}
