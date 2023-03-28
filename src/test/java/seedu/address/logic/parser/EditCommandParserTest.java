package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TO_EDIT_NRIC;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTH_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REGION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REGION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REGION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_STRONG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STRONG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.information.Nric.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no nric specified
        assertParseFailure(parser, VALID_NAME_AMY,
                String.format(MESSAGE_INVALID_TO_EDIT_NRIC, MESSAGE_CONSTRAINTS));

        // no field specified
        assertParseFailure(parser, VALID_NRIC_AMY, MESSAGE_NO_FIELD_PROVIDED);

        // no nric and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid nric
        assertParseFailure(parser, "1337" + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_TO_EDIT_NRIC, MESSAGE_CONSTRAINTS));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                String.format(MESSAGE_INVALID_TO_EDIT_NRIC, MESSAGE_CONSTRAINTS));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, VALID_NRIC_AMY + " i/ string",
                String.format(MESSAGE_INVALID_TO_EDIT_NRIC, MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_NRIC_DESC,
                Nric.MESSAGE_CONSTRAINTS); // invalid nric
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_BIRTH_DATE_DESC,
                BirthDate.MESSAGE_CONSTRAINTS); // invalid age
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_REGION_DESC,
                Region.MESSAGE_CONSTRAINTS); // invalid region
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, VALID_NRIC_AMY + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Elderly} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, VALID_NRIC_AMY + TAG_DESC_STRONG + TAG_DESC_SINGLE + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_NRIC_AMY + TAG_DESC_STRONG + TAG_EMPTY + TAG_DESC_SINGLE,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_NRIC_AMY + TAG_EMPTY + TAG_DESC_STRONG + TAG_DESC_SINGLE,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_NRIC_AMY + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                + VALID_PHONE_AMY + VALID_NRIC_AMY + VALID_BIRTH_DATE_AMY + VALID_REGION_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + PHONE_DESC_BOB + TAG_DESC_SINGLE
                + NRIC_DESC_AMY + EMAIL_DESC_AMY + BIRTH_DATE_DESC_AMY + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + REGION_DESC_AMY + TAG_DESC_STRONG;

        EditDescriptor descriptor = new EditDescriptorBuilder()
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withNric(VALID_NRIC_AMY).withBirthDate(VALID_BIRTH_DATE_AMY)
                .withRegion(VALID_REGION_AMY)
                .withTags(VALID_TAG_SINGLE, VALID_TAG_STRONG).build();

        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditDescriptor descriptor = new EditDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + NAME_DESC_AMY;
        EditDescriptor descriptor = new EditDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_NRIC_BOB + PHONE_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = VALID_NRIC_BOB + EMAIL_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = VALID_NRIC_BOB + ADDRESS_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // nric
        userInput = VALID_NRIC_BOB + NRIC_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withNric(VALID_NRIC_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // age
        userInput = VALID_NRIC_BOB + BIRTH_DATE_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withBirthDate(VALID_BIRTH_DATE_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // region
        userInput = VALID_NRIC_BOB + REGION_DESC_AMY;
        descriptor = new EditDescriptorBuilder().withRegion(VALID_REGION_AMY).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = VALID_NRIC_BOB + TAG_DESC_STRONG;
        descriptor = new EditDescriptorBuilder().withTags(VALID_TAG_STRONG).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_STRONG + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_STRONG
                + NRIC_DESC_AMY + NRIC_DESC_BOB + BIRTH_DATE_DESC_AMY + BIRTH_DATE_DESC_BOB
                + REGION_DESC_AMY + REGION_DESC_BOB
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_SINGLE;

        EditDescriptor descriptor = new EditDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withNric(VALID_NRIC_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).withRegion(VALID_REGION_BOB)
                .withTags(VALID_TAG_STRONG, VALID_TAG_SINGLE).build();

        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditDescriptor descriptor = new EditDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = VALID_NRIC_BOB + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetNric, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Nric targetNric = new Nric(VALID_NRIC_BOB);
        String userInput = VALID_NRIC_BOB + TAG_EMPTY;

        EditDescriptor descriptor = new EditDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetNric, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
