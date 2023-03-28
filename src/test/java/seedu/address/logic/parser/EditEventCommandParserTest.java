package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_TIME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_TIME_DESC_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_TIME_DESC_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_SPORTS_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_SPORTS_DAY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.EventName;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME_CARNIVAL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EVENT_NAME_DESC_CARNIVAL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + EVENT_NAME_DESC_CARNIVAL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + INVALID_EVENT_NAME_DESC, EventName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_START_DATE_TIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid start datetime
        assertParseFailure(parser, "1"
                + INVALID_END_DATE_TIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid end datetime

        // invalid event name followed by valid start datetime
        assertParseFailure(parser, "1"
                + INVALID_EVENT_NAME_DESC + START_DATE_TIME_DESC_CARNIVAL, EventName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC + INVALID_START_DATE_TIME_DESC,
                EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_CARNIVAL + START_DATE_TIME_DESC_CARNIVAL
                + END_DATE_TIME_DESC_CARNIVAL;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_SPORTS_DAY
                + START_DATE_TIME_DESC_SPORTS_DAY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_SPORTS_DAY)
                .withStartDateTime(VALID_START_DATE_TIME_SPORTS_DAY).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // event name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_CARNIVAL;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_CARNIVAL).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start datetime
        userInput = targetIndex.getOneBased() + START_DATE_TIME_DESC_CARNIVAL;
        descriptor = new EditEventDescriptorBuilder().withStartDateTime(VALID_START_DATE_TIME_CARNIVAL).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end datetime
        userInput = targetIndex.getOneBased() + END_DATE_TIME_DESC_CARNIVAL;
        descriptor = new EditEventDescriptorBuilder().withEndDateTime(VALID_END_DATE_TIME_CARNIVAL).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_SPORTS_DAY
                + EVENT_NAME_DESC_CARNIVAL
                + START_DATE_TIME_DESC_CARNIVAL + END_DATE_TIME_DESC_CARNIVAL;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_EVENT_NAME_DESC + EVENT_NAME_DESC_CARNIVAL;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEventName(VALID_EVENT_NAME_CARNIVAL).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + START_DATE_TIME_DESC_CARNIVAL + INVALID_EVENT_NAME_DESC
                + EVENT_NAME_DESC_CARNIVAL + END_DATE_TIME_DESC_CARNIVAL;
        descriptor = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
