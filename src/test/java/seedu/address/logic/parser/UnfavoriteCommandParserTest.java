package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnfavoriteCommand;

class UnfavoriteCommandParserTest {

    private final UnfavoriteCommandParser parser = new UnfavoriteCommandParser();

    @Test
    void parse_validArgs() {
        assertParseSuccess(parser, "1", new UnfavoriteCommand(Index.fromOneBased(1)));
    }

    @Test
    void parse_invalidArgs() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavoriteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavoriteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavoriteCommand.MESSAGE_USAGE));
    }
}
