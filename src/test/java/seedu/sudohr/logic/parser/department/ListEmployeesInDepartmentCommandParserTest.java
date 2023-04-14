package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.sudohr.testutil.TypicalDepartmentNames.HUMAN_RESOURCES_DEPARTMENT_NAME;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.department.ListEmployeesInDepartmentCommand;

public class ListEmployeesInDepartmentCommandParserTest {
    private ListEmployeesInDepartmentCommandParser parser =
            new ListEmployeesInDepartmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, DEPARTMENT_NAME_DESC_HUMAN_RESOURCES,
                new ListEmployeesInDepartmentCommand(HUMAN_RESOURCES_DEPARTMENT_NAME));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListEmployeesInDepartmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_premableNotEmpty_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListEmployeesInDepartmentCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " " + DEPARTMENT_NAME_DESC_ENGINEERING, expectedMessage);

    }

}
