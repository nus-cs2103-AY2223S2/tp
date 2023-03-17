package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.department.AddEmployeeToDepartmentCommand;
import seedu.sudohr.logic.parser.department.AddEmployeeToDepartmentCommandParser;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Id;

public class AddEmployeeToDepartmentCommandParserTest {
    private AddEmployeeToDepartmentCommandParser parser = new AddEmployeeToDepartmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_AMY + DEPARTMENT_NAME_DESC_ENGINEERING,
                new AddEmployeeToDepartmentCommand(new Id("001"), new DepartmentName("Engineering")));
        assertParseSuccess(parser, ID_DESC_AMY + DEPARTMENT_NAME_DESC_ENGINEERING,
                new AddEmployeeToDepartmentCommand(new Id("001"), new DepartmentName("Engineering")));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEmployeeToDepartmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, DEPARTMENT_NAME_DESC_ENGINEERING, expectedMessage);
        assertParseFailure(parser, ID_DESC_AMY, expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEmployeeToDepartmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_ID_DESC + DEPARTMENT_NAME_DESC_ENGINEERING,
                Id.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, ID_DESC_AMY + INVALID_DEPARTMENT_NAME_DESC,
                DepartmentName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_ID_DESC + INVALID_DEPARTMENT_NAME_DESC, Id.MESSAGE_CONSTRAINTS);
    }
}
