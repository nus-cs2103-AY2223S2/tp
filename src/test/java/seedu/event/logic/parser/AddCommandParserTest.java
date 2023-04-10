package seedu.event.logic.parser;

import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.END_TIME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.END_TIME_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.event.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.event.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.event.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.event.logic.commands.CommandTestUtil.START_TIME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.START_TIME_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.event.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.event.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_END_TIME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_START_TIME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.event.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.event.testutil.TypicalEvents.AMY;
import static seedu.event.testutil.TypicalEvents.BOB;

import org.junit.jupiter.api.Test;

import seedu.event.logic.commands.AddCommand;
import seedu.event.model.event.Address;
import seedu.event.model.event.Event;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;
import seedu.event.testutil.EventBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedEvent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedEvent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedEvent));

        // multiple tags - all accepted
        Event expectedEventMultipleTags = new EventBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedEventMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Event expectedEvent = new EventBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + ADDRESS_DESC_AMY
                        + START_TIME_DESC_AMY + END_TIME_DESC_AMY,
                new AddCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + START_TIME_DESC_AMY + END_TIME_DESC_AMY,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB
                        + START_TIME_DESC_AMY + END_TIME_DESC_AMY,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB
                        + START_TIME_DESC_AMY + END_TIME_DESC_AMY,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_START_TIME_BOB + END_TIME_DESC_AMY,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + START_TIME_DESC_AMY + VALID_END_TIME_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_ADDRESS_BOB
                        + VALID_START_TIME_BOB + VALID_END_TIME_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + START_TIME_DESC_AMY + END_TIME_DESC_AMY
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Rate.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_START_TIME_DESC + END_TIME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Time.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + INVALID_END_TIME_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Time.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_ADDRESS_DESC
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + START_TIME_DESC_BOB + END_TIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
