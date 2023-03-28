package seedu.address.logic.parser;

import static seedu.address.logic.commands.ShortcutCommand.MESSAGE_INVALID_SHORTCUT;
import static seedu.address.logic.commands.ShortcutCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.ShortcutCommandParser.CommandType;

public class ShortcutCommandParserTest {

    private ShortcutCommandParser parser = new ShortcutCommandParser();

    @Test
    public void parse_validArgs_returnsShortcutCommand() {
        assertParseSuccess(parser, "help he", new ShortcutCommand(CommandType.HELP, "he"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "eat e", String.format(MESSAGE_INVALID_SHORTCUT, MESSAGE_USAGE));
    }
}
