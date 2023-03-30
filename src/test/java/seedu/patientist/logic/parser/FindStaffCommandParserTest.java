package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.FindStaffCommand;
import seedu.patientist.model.person.staff.StaffIdContainsKeywordsPredicate;
import seedu.patientist.model.person.staff.StaffNameContainsKeywordsPredicate;

public class FindStaffCommandParserTest {
    private FindStaffCommandParser parser = new FindStaffCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStaffCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {
        // no leading and trailing whitespaces
        FindStaffCommand expectedFindCommand =
                new FindStaffCommand(new StaffNameContainsKeywordsPredicate(List.of("Alice")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice", expectedFindCommand);

        // no leading and trailing whitespaces
        expectedFindCommand =
                new FindStaffCommand(new StaffIdContainsKeywordsPredicate(Arrays.asList("A123")));
        assertParseSuccess(parser, " " + PREFIX_ID + "A123", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, PREFIX_NAME + "Alice " + PREFIX_ID + "A123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStaffCommand.MESSAGE_USAGE));
    }
}
