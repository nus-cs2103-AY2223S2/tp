package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.patientist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.DeletePatientCommand;
import seedu.patientist.model.person.IdNumber;

public class DeletePatientCommandParserTest {
    private DeletePatientCommandParser deletePatientCommandParser = new DeletePatientCommandParser();
    @Test
    public void parse_validArgs_returnsDeletePatientCommand() {
        DeletePatientCommand expected = new DeletePatientCommand(new IdNumber("A12345A"));
        assertParseSuccess(deletePatientCommandParser, PREAMBLE_WHITESPACE + " id/A12345A", expected);
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParserException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE);
        assertParseFailure(deletePatientCommandParser, PREAMBLE_NON_EMPTY + "id/A12345A", expectedMessage);
    }

    @Test
    public void parse_moreThanOneInput_throwsParserException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE);
        assertParseFailure(deletePatientCommandParser, " id/A12345A A12345B", expectedMessage);
    }

    @Test
    public void parse_noPrefixId_throwsParserException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE);
        assertParseFailure(deletePatientCommandParser, " A12345A", expectedMessage);
    }

    @Test
    public void parse_invalidIdNumber_throwsParserException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE);
        assertParseFailure(deletePatientCommandParser, " id/!@#$%", expectedMessage);
    }
}
