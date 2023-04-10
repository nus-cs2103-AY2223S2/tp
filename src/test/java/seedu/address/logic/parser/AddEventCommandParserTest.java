package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_TIME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_TIME_DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_TIME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_TIME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_TIME_DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_CARNIVAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private final AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(CARNIVAL).withName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_NAME_DESC_CARNIVAL
                + START_DATE_TIME_DESC_CARNIVAL + END_DATE_TIME_DESC_CARNIVAL, new AddEventCommand(expectedEvent));

        // multiple event names - last event name accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_SPORTS_DAY + EVENT_NAME_DESC_CARNIVAL
                + START_DATE_TIME_DESC_CARNIVAL + END_DATE_TIME_DESC_CARNIVAL, new AddEventCommand(expectedEvent));

        // multiple start date times - last start date time accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_CARNIVAL + START_DATE_TIME_DESC_SPORTS_DAY
                + START_DATE_TIME_DESC_CARNIVAL + END_DATE_TIME_DESC_CARNIVAL, new AddEventCommand(expectedEvent));

        // multiple end date times - last end date time accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_CARNIVAL + START_DATE_TIME_DESC_CARNIVAL
                + END_DATE_TIME_DESC_SPORTS_DAY + END_DATE_TIME_DESC_CARNIVAL, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing event name prefix
        assertParseFailure(parser, VALID_EVENT_NAME_CARNIVAL + START_DATE_TIME_DESC_CARNIVAL
                        + END_DATE_TIME_DESC_CARNIVAL,
                expectedMessage);

        // missing start date time prefix
        assertParseFailure(parser, EVENT_NAME_DESC_CARNIVAL + VALID_START_DATE_TIME_CARNIVAL
                        + END_DATE_TIME_DESC_CARNIVAL,
                expectedMessage);

        // missing end date time prefix
        assertParseFailure(parser, EVENT_NAME_DESC_CARNIVAL + START_DATE_TIME_DESC_CARNIVAL
                        + VALID_END_DATE_TIME_CARNIVAL,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid event name
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC + START_DATE_TIME_DESC_CARNIVAL
                + END_DATE_TIME_DESC_CARNIVAL, EventName.MESSAGE_CONSTRAINTS);

        // invalid start date time format
        assertParseFailure(parser, EVENT_NAME_DESC_CARNIVAL + INVALID_START_DATE_TIME_DESC
                + END_DATE_TIME_DESC_CARNIVAL, DateTime.MESSAGE_CONSTRAINTS);

        // invalid end date time format
        assertParseFailure(parser, EVENT_NAME_DESC_CARNIVAL + START_DATE_TIME_DESC_CARNIVAL
                + INVALID_END_DATE_TIME_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC + START_DATE_TIME_DESC_CARNIVAL
                + INVALID_END_DATE_TIME_DESC, EventName.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_invalidDateTimeRange_failure() {
        // start date time is after end date time
        assertParseFailure(parser, EVENT_NAME_DESC_CARNIVAL + INVALID_START_DATE_TIME_DESC_CARNIVAL
                + END_DATE_TIME_DESC_CARNIVAL, DateTime.MESSAGE_CONSTRAINTS);
    }
}
