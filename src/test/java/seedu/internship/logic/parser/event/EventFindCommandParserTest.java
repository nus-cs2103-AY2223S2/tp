package seedu.internship.logic.parser.event;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.END_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_END_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.internship.logic.commands.CommandTestUtil.NAME_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.START_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_END_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_NAME_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_START_EM11;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.event.EventFindCommand;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;




public class EventFindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE);

    private EventFindCommandParser parser = new EventFindCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // missing all fields
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_START_DESC, Start.MESSAGE_CONSTRAINTS); // invalid start
        assertParseFailure(parser, INVALID_END_DESC, End.MESSAGE_CONSTRAINTS); // invalid end


        // invalid name followed by valid start
        assertParseFailure(parser, INVALID_NAME_DESC + START_DESC_EM11, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name.
        assertParseFailure(parser, NAME_DESC_EM11 + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_END_DESC + INVALID_START_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = NAME_DESC_EM11 + START_DESC_EM11 + END_DESC_EM11;

        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name(VALID_NAME_EM11));
        descriptor.setStart(new Start(LocalDateTime.parse(VALID_START_EM11,
                Start.NUMERIC_DATE_TIME_FORMATTER)));
        descriptor.setEnd(new End(LocalDateTime.parse(VALID_END_EM11,
                End.NUMERIC_DATE_TIME_FORMATTER)));
        EventFindCommand expectedCommand = new EventFindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = " na/Interview";

        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("Interview"));
        EventFindCommand expectedCommand = new EventFindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

