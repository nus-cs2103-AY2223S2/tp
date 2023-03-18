package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_ID_DESC_ZERO;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.sudohr.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalIds.ID_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.EditCommand;
import seedu.sudohr.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.sudohr.model.employee.Address;
import seedu.sudohr.model.employee.Email;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.Name;
import seedu.sudohr.model.employee.Phone;
import seedu.sudohr.model.tag.Tag;
import seedu.sudohr.testutil.EditEmployeeDescriptorBuilder;

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
        assertParseFailure(parser, "eid/1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "eid/-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "eid/0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as eid
        assertParseFailure(parser, "eid/1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as eid
        assertParseFailure(parser, "eid/1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "eid/1" + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid id
        assertParseFailure(parser, "eid/1" + INVALID_ID_DESC_ZERO, Id.MESSAGE_CONSTRAINTS); // invalid id; all 0s
        assertParseFailure(parser, "eid/1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "eid/1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "eid/1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "eid/1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "eid/1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid id followed by invalid phone
        assertParseFailure(parser, "eid/1" + INVALID_ID_DESC + INVALID_PHONE_DESC, Id.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "eid/1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "eid/1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Employee} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "eid/1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "eid/1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "eid/1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "eid/1" + VALID_ID_AMY + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_ADDRESS_AMY + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = EID_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + ID_DESC_AMY + TAG_DESC_FRIEND;

        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_AMY)
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(new Id(VALID_ID_BOB), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = EID_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Id(VALID_ID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // change to ID
        userInput =  EID_DESC_AMY + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Id(VALID_ID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // effectively change to ID
        userInput =  EID_DESC_AMY + ID_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Id(VALID_ID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        String targetIdDesc = EID_DESC_BOB;

        // id
        String userInput = targetIdDesc + ID_DESC_AMY;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name
        userInput = targetIdDesc + NAME_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY).build();
        expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIdDesc + PHONE_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIdDesc + EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIdDesc + ADDRESS_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIdDesc + TAG_DESC_FRIEND;
        descriptor = new EditEmployeeDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(new Id(targetIdDesc), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = EID_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(new Id(VALID_ID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = EID_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(new Id(VALID_ID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EID_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(new Id(VALID_ID_BOB), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = EID_DESC_BOB + TAG_EMPTY;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(new Id(VALID_ID_BOB), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
