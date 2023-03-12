package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_HUMAN_RESOURCES;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.EditDepartmentCommand;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.testutil.EditDepartmentDescriptorBuilder;

public class EditDepartmentCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDepartmentCommand.MESSAGE_USAGE);

    private EditDepartmentCommandParser parser = new EditDepartmentCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, VALID_DEPARTMENT_NAME_ENGINEERING, EditDepartmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "&Engineering", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "Engineering i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DEPARTMENT_NAME_DESC,
                DepartmentName.MESSAGE_CONSTRAINTS); // invalid name
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        DepartmentName targetDepartment = new DepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING);
        String userInput = targetDepartment + DEPARTMENT_NAME_DESC_HUMAN_RESOURCES;

        EditDepartmentCommand.EditDepartmentDescriptor descriptor =
                new EditDepartmentDescriptorBuilder()
                        .withName(new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES)).build();
        EditDepartmentCommand expectedCommand = new EditDepartmentCommand(targetDepartment, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        DepartmentName targetDepartment = new DepartmentName("Sales");
        String userInput = targetDepartment + DEPARTMENT_NAME_DESC_HUMAN_RESOURCES + DEPARTMENT_NAME_DESC_ENGINEERING;

        EditDepartmentCommand.EditDepartmentDescriptor descriptor = new EditDepartmentDescriptorBuilder()
                .withName(new DepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING)).build();
        EditDepartmentCommand expectedCommand = new EditDepartmentCommand(targetDepartment, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        DepartmentName targetDepartment = new DepartmentName("Sales");
        String userInput = targetDepartment + INVALID_DEPARTMENT_NAME_DESC
                + DEPARTMENT_NAME_DESC_HUMAN_RESOURCES + DEPARTMENT_NAME_DESC_ENGINEERING;
        EditDepartmentCommand.EditDepartmentDescriptor descriptor = new EditDepartmentDescriptorBuilder()
                .withName(new DepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING)).build();
        EditDepartmentCommand expectedCommand = new EditDepartmentCommand(targetDepartment, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
