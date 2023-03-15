package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckCommand;

/**
 * Parser test for check command.
 */
class CheckCommandParserTest {
    private CheckCommandParser parser = new CheckCommandParser();

    @Test
    public void parse_validArgs_returnsCheckCommand() {
        assertParseSuccess(parser, "1", new CheckCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }
}
