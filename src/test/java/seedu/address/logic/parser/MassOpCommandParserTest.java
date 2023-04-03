package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MassOpCommand;

public class MassOpCommandParserTest {

    private final MassOpCommandParser parser = new MassOpCommandParser();

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "zfs/abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassOpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_someArgs_returnsCommand() {
        // no leading and trailing whitespaces
        MassOpCommand expectedMassOpCommand =
                new MassOpCommand("tag friends");
        assertParseSuccess(parser, "tag friends", expectedMassOpCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "tag \t friends \n \t\t", expectedMassOpCommand);
    }
}
