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
                new ListWardPatientsCommand("Block A Ward 1");
        assertParseSuccess(parser, "Block A Ward 1", expectedFindCommand);
    }

    @Test
    public void parse_withPrefixes_throwsParseException() {
        assertParseFailure(parser, " w/Block A Ward 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardPatientsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_withMultiplePrefixes_throwsParseException() {
        assertParseFailure(parser, "w/Block A Ward 1 n/jonnie",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardPatientsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_withMultipleSamePrefixes_throwsParseException() {
        assertParseFailure(parser, "w/Block A Ward 1 w/Block A Ward 3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListWardPatientsCommand.MESSAGE_USAGE));
    }
}
