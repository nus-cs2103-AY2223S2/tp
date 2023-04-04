package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_BEFORE_START_TIME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ezschedule.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ezschedule.logic.commands.CommandTestUtil.START_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.START_TIME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_END_TIME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ezschedule.testutil.TypicalEvents.EVENT_B;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.Messages;
import ezschedule.logic.commands.AddCommand;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;
import ezschedule.testutil.EventBuilder;

public class AddCommandParserTest {

    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(EVENT_B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_B + DATE_DESC_B
                + START_TIME_DESC_B + END_TIME_DESC_B, new AddCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_A + NAME_DESC_B + DATE_DESC_B
                + START_TIME_DESC_B + END_TIME_DESC_B, new AddCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_B + DATE_DESC_A + DATE_DESC_B
                + START_TIME_DESC_B + END_TIME_DESC_B, new AddCommand(expectedEvent));

        // multiple start times - last start time accepted
        assertParseSuccess(parser, NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_A
                + START_TIME_DESC_B + END_TIME_DESC_B, new AddCommand(expectedEvent));

        // multiple end times - last end time accepted
        assertParseSuccess(parser, NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_B
                + END_TIME_DESC_A + END_TIME_DESC_B, new AddCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryPrefixesMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_B + DATE_DESC_B + START_TIME_DESC_B + END_TIME_DESC_B,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_B + VALID_DATE_B + START_TIME_DESC_B + END_TIME_DESC_B,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B + VALID_START_TIME_B + END_TIME_DESC_B,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_B + VALID_END_TIME_B,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_B + VALID_DATE_B + VALID_START_TIME_B + VALID_END_TIME_B,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldsEmpty_failure() {
        // missing name field
        assertParseFailure(parser, " " + PREFIX_NAME + DATE_DESC_B + START_TIME_DESC_B + END_TIME_DESC_B,
                Name.MESSAGE_CONSTRAINTS);

        // missing date field
        assertParseFailure(parser, NAME_DESC_B + " " + PREFIX_DATE + START_TIME_DESC_B + END_TIME_DESC_B,
                Date.MESSAGE_CONSTRAINTS);

        // missing start time field
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B + " " + PREFIX_START + END_TIME_DESC_B,
                Time.MESSAGE_CONSTRAINTS);

        // missing end time field
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_B + " " + PREFIX_END,
                Time.MESSAGE_CONSTRAINTS);

        // multiple missing fields - date followed by start time
        assertParseFailure(parser, NAME_DESC_A
                + " " + PREFIX_DATE + " " + PREFIX_START + END_TIME_DESC_B, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_B
                + START_TIME_DESC_B + END_TIME_DESC_B, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_B + INVALID_DATE_DESC
                + START_TIME_DESC_B + END_TIME_DESC_B, Date.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B
                + INVALID_START_TIME_DESC + END_TIME_DESC_B, Time.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B
                + START_TIME_DESC_B + INVALID_END_TIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid end time before start time
        assertParseFailure(parser, NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_B
                + END_TIME_BEFORE_START_TIME_DESC_B, Messages.MESSAGE_EVENT_END_TIME_EARLIER_THAN_START_TIME);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_B
                + START_TIME_DESC_B + INVALID_END_TIME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_B + DATE_DESC_B + START_TIME_DESC_B
                + END_TIME_DESC_B, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
