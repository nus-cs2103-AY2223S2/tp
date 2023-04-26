package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.careflow.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.patientcommands.FindCommand;
import seedu.careflow.model.patient.NameContainsKeywordsPredicate;

class FindCommandParserTest {

    private final FindCommandParser findCommandParser = new FindCommandParser();

    @Test
    void parse_validFindPatientExists_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Roy", "Balakrishnan")));
        assertParseSuccess(findCommandParser, "Roy Balakrishnan", expectedFindCommand);
        assertParseSuccess(findCommandParser, " \n Roy \n \t Balakrishnan  \t", expectedFindCommand);
    }


    @Test
    void parse_invalidFindNoInput_throwsParseException() {
        assertParseFailure(findCommandParser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
