package seedu.task.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.AlertCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AlertCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the AlertCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AlertCommandParserTest {
    private AlertCommandParser parser = new AlertCommandParser();

    @Test
    public void parser_validArgs_returnsAlertCommand() {
        assertParseSuccess(parser, "1", new AlertCommand(1));

        // Test for multiple arguments but all valid (should take the last one)
        assertParseSuccess(parser, "1 1 1 1 1 2", new AlertCommand(2));
        assertParseSuccess(parser, "1  1   1  2", new AlertCommand(2));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AlertCommand.MESSAGE_USAGE));

        // Test if there are multi arguments but some are invalid
        assertParseFailure(parser, "a 1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AlertCommand.MESSAGE_USAGE));
    }

    @Test
    public void onlyDigits_onlyDigits_returnsTrue() {
        AlertCommandParser parser = new AlertCommandParser();
        assertTrue(parser.onlyDigits("1 2 3 4 5 6 7 8"));
        assertTrue(parser.onlyDigits("12034 9091   10290 12   3"));
    }

    @Test
    public void onlyDigits_notOnlyDigits_returnsFalse() {
        AlertCommandParser parser = new AlertCommandParser();
        assertFalse(parser.onlyDigits("a b c d e f g"));
        assertFalse(parser.onlyDigits("a 2 4 0 v 1 2"));
    }
}
