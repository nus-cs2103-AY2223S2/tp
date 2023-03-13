package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UploadCommand;

class UploadCommandParserTest {

    private static final String SOURCE = "source/path.txt";

    private UploadCommandParser parser = new UploadCommandParser();

    @Test
    public void parse_validArgs_returnsUploadCommand() {
        assertParseSuccess(parser, SOURCE, new UploadCommand(Paths.get(SOURCE),
                Paths.get(UploadCommandParser.DESTINATION_FILEPATH)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UploadCommand.MESSAGE_USAGE));
    }
}
