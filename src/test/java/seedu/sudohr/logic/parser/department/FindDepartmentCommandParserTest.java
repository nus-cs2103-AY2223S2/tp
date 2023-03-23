package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.department.FindDepartmentCommand;
import seedu.sudohr.model.department.DepartmentNameContainsKeywordsPredicate;

public class FindDepartmentCommandParserTest {

    private FindDepartmentCommandParser parser = new FindDepartmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDepartmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindDepartmentCommand expectedFindDepartmentCommand =
                new FindDepartmentCommand(new DepartmentNameContainsKeywordsPredicate(
                        Arrays.asList("Human", "Engineering")));
        assertParseSuccess(parser, "Human Engineering", expectedFindDepartmentCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Human \n \t Engineering  \t", expectedFindDepartmentCommand);
    }

}
