package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.ListEmployeeDepartmentCommand;
import seedu.sudohr.model.department.DepartmentContainsEmployeePredicate;
import seedu.sudohr.model.employee.Id;

public class ListEmployeeDepartmentCommandParserTest {

    private ListEmployeeDepartmentCommandParser parser = new ListEmployeeDepartmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEmployeeDepartmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListEmployeeDepartmentCommand() {
        // no leading and trailing whitespaces
        ListEmployeeDepartmentCommand expectedListEmployeeDepartmentCommand =
                new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(new Id("001")));
        assertParseSuccess(parser, ID_DESC_AMY, expectedListEmployeeDepartmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " +  ID_DESC_AMY + "\n", expectedListEmployeeDepartmentCommand);
    }

}
