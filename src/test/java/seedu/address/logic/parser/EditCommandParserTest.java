package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_BEN;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BEN;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BEN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.*;
import seedu.address.model.tag.GroupTag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + Prefix.GROUP_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ALEX, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ALEX, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_GROUP_DESC, GroupTag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_ALEX, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BEN + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + VALID_GROUP_2_DESC
                + VALID_GROUP_1_DESC + TAG_EMPTY, GroupTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + VALID_GROUP_2_DESC
                + TAG_EMPTY + VALID_GROUP_1_DESC, GroupTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + VALID_GROUP_2_DESC
                + VALID_GROUP_1_DESC, GroupTag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + ADDRESS_ALEX + PHONE_ALEX,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BEN + VALID_GROUP_1_DESC
                + EMAIL_DESC_ALEX + ADDRESS_DESC_ALEX + NAME_DESC_ALEX + VALID_GROUP_2_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(NAME_ALEX)
                .withPhone(PHONE_BEN).withEmail(EMAIL_ALEX).withAddress(ADDRESS_ALEX)
                .withGroupTags(VALID_GROUP_1, VALID_GROUP_2).build();
        EditCommand expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BEN + EMAIL_DESC_ALEX;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(PHONE_BEN)
                .withEmail(EMAIL_ALEX).build();
        EditCommand expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        ContactIndex contactIndex = new ContactIndex(targetIndex.getOneBased());
        String userInput = targetIndex.getOneBased() + NAME_DESC_ALEX;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(NAME_ALEX).build();
        EditCommand expectedCommand = new EditCommand(contactIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_ALEX;
        descriptor = new EditPersonDescriptorBuilder().withPhone(PHONE_ALEX).build();
        expectedCommand = new EditCommand(contactIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_ALEX;
        descriptor = new EditPersonDescriptorBuilder().withEmail(EMAIL_ALEX).build();
        expectedCommand = new EditCommand(contactIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_ALEX;
        descriptor = new EditPersonDescriptorBuilder().withAddress(ADDRESS_ALEX).build();
        expectedCommand = new EditCommand(contactIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + VALID_GROUP_2_DESC;
        descriptor = new EditPersonDescriptorBuilder().withGroupTags(VALID_GROUP_2).build();
        expectedCommand = new EditCommand(contactIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_ALEX + ADDRESS_DESC_ALEX + EMAIL_DESC_ALEX
                + VALID_GROUP_2_DESC + PHONE_DESC_ALEX + ADDRESS_DESC_ALEX + EMAIL_DESC_ALEX + VALID_GROUP_2_DESC
                + PHONE_DESC_BEN + ADDRESS_DESC_BEN + EMAIL_DESC_BEN + VALID_GROUP_1_DESC;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(PHONE_BEN)
                .withEmail(EMAIL_BEN).withAddress(ADDRESS_BEN)
                .withGroupTags(VALID_GROUP_2, VALID_GROUP_1)
                .build();
        EditCommand expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BEN;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(PHONE_BEN).build();
        EditCommand expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BEN + INVALID_PHONE_DESC + ADDRESS_DESC_BEN
                + PHONE_DESC_BEN;
        descriptor = new EditPersonDescriptorBuilder().withPhone(PHONE_BEN).withEmail(EMAIL_BEN)
                .withAddress(ADDRESS_BEN).build();
        expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGroupTags().build();
        EditCommand expectedCommand = new EditCommand(new ContactIndex(targetIndex.getOneBased()), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
