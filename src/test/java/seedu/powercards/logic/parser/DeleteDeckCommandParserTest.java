package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.deckcommands.DeleteDeckCommand;

public class DeleteDeckCommandParserTest {

    private DeleteDeckCommandParser parser = new DeleteDeckCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteDeckCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeckCommand.MESSAGE_USAGE));
    }
}
