package t154.CLIpboard.logic.parser;

import static t154.CLIpboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static t154.CLIpboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static t154.CLIpboard.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import t154.CLIpboard.logic.commands.UploadCommand;

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
