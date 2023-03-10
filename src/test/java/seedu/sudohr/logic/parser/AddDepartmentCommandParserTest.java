package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_HUMAN_RESOURCES;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.AddDepartmentCommand;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.testutil.DepartmentBuilder;

public class AddDepartmentCommandParserTest {
    private AddDepartmentCommandParser parser = new AddDepartmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Department expectedDepartment = new DepartmentBuilder().withDepartmentName("Engineering").build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEPARTMENT_NAME_DESC_ENGINEERING,
                new AddDepartmentCommand(expectedDepartment));

        // multiple names - last name accepted
        assertParseSuccess(parser, DEPARTMENT_NAME_DESC_HUMAN_RESOURCES + DEPARTMENT_NAME_DESC_ENGINEERING,
                new AddDepartmentCommand(expectedDepartment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDepartmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DEPARTMENT_NAME_HUMAN_RESOURCES, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_DEPARTMENT_NAME_DESC, DepartmentName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DEPARTMENT_NAME_DESC_ENGINEERING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDepartmentCommand.MESSAGE_USAGE));
    }
}
