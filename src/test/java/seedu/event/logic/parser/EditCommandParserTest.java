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
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.START_TIME_DESC_AMY;
import static seedu.event.logic.commands.CommandTestUtil.START_TIME_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.event.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.event.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_END_TIME_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_END_TIME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_START_TIME_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_START_TIME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.event.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.event.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.event.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.event.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.event.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.EditCommand;
import seedu.event.logic.commands.EditCommand.EditEventDescriptor;
import seedu.event.model.event.Address;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;
import seedu.event.testutil.EditEventDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Rate.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        // invalid timing
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC
                + " " + INVALID_END_TIME_DESC, Time.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + START_TIME_DESC_AMY
                + " " + INVALID_END_TIME_DESC, Time.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC
                + " " + END_TIME_DESC_AMY, Time.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Rate.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + START_TIME_DESC_BOB
                + END_TIME_DESC_BOB
                + TAG_DESC_FRIEND;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_AMY)
                .withStartTime(VALID_START_TIME_BOB)
                .withEndTime(VALID_END_TIME_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder()
                .withStartTime(VALID_START_TIME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_AMY;
        descriptor = new EditEventDescriptorBuilder()
                .withEndTime(VALID_END_TIME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + START_TIME_DESC_AMY + END_TIME_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + START_TIME_DESC_BOB + END_TIME_DESC_BOB
                + TAG_DESC_HUSBAND;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withStartTime(VALID_START_TIME_BOB).withEndTime(VALID_END_TIME_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditEventDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
