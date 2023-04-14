package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_ONE;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid department
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_DEPARTMENT_DESC,
                Department.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                "1" + CommandTestUtil.TAG_DESC_MANAGER + CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + CommandTestUtil.TAG_DESC_MANAGER + TAG_EMPTY + CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + TAG_EMPTY + CommandTestUtil.TAG_DESC_MANAGER + CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_EMAIL_DESC
                        + CommandTestUtil.VALID_ADDRESS_AMY + CommandTestUtil.VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        EmployeeId targetEmployeeId = EMPLOYEE_ID_TWO;
        String userInput = "2" + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.DEPARTMENT_DESC_AMY + CommandTestUtil.TAG_DESC_MANAGER;

        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).withAddress(CommandTestUtil.VALID_ADDRESS_AMY)
                .withDepartment(CommandTestUtil.VALID_DEPARTMENT_AMY)
                .withTags(CommandTestUtil.VALID_TAG_SOFTWARE_ENGINEER, CommandTestUtil.VALID_TAG_MANAGER).build();
        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        EmployeeId targetEmployeeId = EMPLOYEE_ID_ONE;
        String userInput = "1" + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY;

        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        EmployeeId targetEmployeeId = EMPLOYEE_ID_ONE;
        String userInput = "1" + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = "1" + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = "1" + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = "1" + CommandTestUtil.ADDRESS_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withAddress(CommandTestUtil.VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = "1" + CommandTestUtil.DEPARTMENT_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withDepartment(CommandTestUtil.VALID_DEPARTMENT_AMY).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = "1" + CommandTestUtil.TAG_DESC_MANAGER;
        descriptor = new EditEmployeeDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_MANAGER).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        EmployeeId targetEmployeeId = EMPLOYEE_ID_ONE;
        String userInput = "1"
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.DEPARTMENT_DESC_AMY + CommandTestUtil.TAG_DESC_MANAGER
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.DEPARTMENT_DESC_AMY + CommandTestUtil.TAG_DESC_MANAGER
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.DEPARTMENT_DESC_BOB + CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER;

        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withDepartment(CommandTestUtil.VALID_DEPARTMENT_BOB)
                .withTags(CommandTestUtil.VALID_TAG_MANAGER, CommandTestUtil.VALID_TAG_SOFTWARE_ENGINEER).build();

        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        EmployeeId targetEmployeeId = EMPLOYEE_ID_ONE;
        String userInput = "1" + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.PHONE_DESC_BOB;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = "1" + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.DEPARTMENT_DESC_BOB;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withDepartment(CommandTestUtil.VALID_DEPARTMENT_BOB).build();
        expectedCommand = new EditCommand(targetEmployeeId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        EmployeeId targetEmployeeId = EMPLOYEE_ID_TWO;
        String userInput = "2" + TAG_EMPTY;

        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetEmployeeId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
