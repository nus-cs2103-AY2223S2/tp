package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.ListWardPatientsCommand;

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
                new ListWardPatientsCommand("Alice");
        assertParseSuccess(parser, "Alice", expectedFindCommand);
    }
}
