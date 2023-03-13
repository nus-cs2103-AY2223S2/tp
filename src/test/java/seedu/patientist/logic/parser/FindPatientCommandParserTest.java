package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.FindPatientCommand;
import seedu.patientist.model.person.patient.PatientNameContainsKeywordsPredicate;
import seedu.patientist.model.person.patient.PidContainsKeywordsPredicate;

public class FindPatientCommandParserTest {
    private FindPatientCommandParser parser = new FindPatientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {
        // no leading and trailing whitespaces
        FindPatientCommand expectedFindCommand =
                new FindPatientCommand(new PatientNameContainsKeywordsPredicate(List.of("Alice")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice", expectedFindCommand);

        // no leading and trailing whitespaces
        expectedFindCommand =
                new FindPatientCommand(new PidContainsKeywordsPredicate(Arrays.asList("A123")));
        assertParseSuccess(parser, " " + PREFIX_PID + "A123", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, PREFIX_NAME + "Alice " + PREFIX_PID + "A123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
    }
}
