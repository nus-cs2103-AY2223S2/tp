package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_POSTAL_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.POSTAL_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.POSTAL_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.dengue.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.EditCommand;
import seedu.dengue.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;
import seedu.dengue.testutil.EditPersonDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_POSTAL_DESC, Postal.MESSAGE_CONSTRAINTS); // invalid postal
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_AGE_DESC, Age.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid postal followed by valid email
        assertParseFailure(parser, "1" + INVALID_POSTAL_DESC + EMAIL_DESC_AMY, Postal.MESSAGE_CONSTRAINTS);

        // valid postal followed by invalid postal. The test case for invalid postal followed by valid postal
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + POSTAL_DESC_BOB + INVALID_POSTAL_DESC, Postal.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DATE_DESC
                        + VALID_AGE_AMY + VALID_POSTAL_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPostal(VALID_POSTAL_BOB).withDate(VALID_DATE_AMY).withAddress(VALID_AGE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPostal(VALID_POSTAL_BOB)
                .withDate(VALID_DATE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // postal
        userInput = targetIndex.getOneBased() + POSTAL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPostal(VALID_POSTAL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withDate(VALID_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_AGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + POSTAL_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + POSTAL_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + POSTAL_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPostal(VALID_POSTAL_BOB)
                .withDate(VALID_DATE_BOB).withAddress(VALID_AGE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_POSTAL_DESC + POSTAL_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPostal(VALID_POSTAL_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_POSTAL_DESC + ADDRESS_DESC_BOB
                + POSTAL_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPostal(VALID_POSTAL_BOB)
                .withDate(VALID_DATE_BOB)
                .withAddress(VALID_AGE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
