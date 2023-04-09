package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.UpdatePatientWardCommand;

public class UpdatePatientWardParserTest {

    private UpdatePatientWardParser parser = new UpdatePatientWardParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "2 w/Block A Ward 2",
                new UpdatePatientWardCommand(INDEX_SECOND_PERSON, "Block A Ward 2"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a w/Block A Ward 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePatientWardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "1 n/Block A Ward 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePatientWardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefixes_throwsParseException() {
        assertParseFailure(parser, "1 w/Block A Ward 2 n/Johnnie",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePatientWardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleArgs_throwsParseException() {
        assertParseFailure(parser, "1 w/Block A Ward 2 w/Block A Ward 3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UpdatePatientWardCommand.MESSAGE_USAGE));
    }
}
