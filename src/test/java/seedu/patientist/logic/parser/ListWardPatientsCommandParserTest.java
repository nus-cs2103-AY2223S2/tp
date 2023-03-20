package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.ListWardPatientsCommand;
import seedu.patientist.model.person.patient.PatientInWardPredicate;

public class ListWardPatientsCommandParserTest {
    private ListWardPatientsCommandParser parser = new ListWardPatientsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardPatientsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ListWardPatientsCommand expectedFindCommand =
                new ListWardPatientsCommand(new PatientInWardPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }
}
