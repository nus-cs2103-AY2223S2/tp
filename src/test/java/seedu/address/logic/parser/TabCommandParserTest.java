package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_ADDRESS_BOOK_TAB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TabCommand;

class TabCommandParserTest {

    private final TabCommandParser parser = new TabCommandParser();

    @Test
    void parse_validArgs_returnTabCommand() {
        assertParseSuccess(parser, "1", new TabCommand(INDEX_ADDRESS_BOOK_TAB));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
    }
}
