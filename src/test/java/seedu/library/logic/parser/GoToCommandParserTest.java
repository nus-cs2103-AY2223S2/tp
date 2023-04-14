package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOKMARK;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.GoToCommand;



public class GoToCommandParserTest {
    private GoToCommandParser parser = new GoToCommandParser();

    @Test
    public void parse_validArgs_returnsGoToCommand() {
        assertParseSuccess(parser, "1", new GoToCommand(INDEX_FIRST_BOOKMARK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCommand.MESSAGE_USAGE));
    }
}
