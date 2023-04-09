package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.commands.OpenCommand;

public class OpenCommandParserTest {

    private final OpenCommandParser parser = new OpenCommandParser();

    @Test
    public void parse_noPlatform_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPerson_throwsParseException() {
        assertParseFailure(parser, "tg/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
    }
}
