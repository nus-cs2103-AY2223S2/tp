package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INDICATE_POSITIVE;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.RedoCommand;
import seedu.dengue.logic.commands.UndoCommand;


public class UndoRedoCommandParserTest {
    private UndoCommandParser undoParser = new UndoCommandParser();
    private RedoCommandParser redoParser = new RedoCommandParser();

    @Test
    public void parse_positiveValidArguments_success() {
        assertParseSuccess(undoParser, "1", new UndoCommand(1));
        assertParseSuccess(undoParser, "  12  ", new UndoCommand(12));
        assertParseSuccess(redoParser, "  21  ", new RedoCommand(21));
        assertParseSuccess(redoParser, " 999   ", new RedoCommand(999));
    }

    @Test
    public void parse_invalidArguments_failure() {
        // negative or non-positive integers
        assertParseFailure(
                undoParser, "-1", String.format(
                        MESSAGE_INDICATE_POSITIVE, UndoCommand.MESSAGE_USAGE));

        assertParseFailure(
                undoParser, "0", String.format(
                        MESSAGE_INDICATE_POSITIVE, UndoCommand.MESSAGE_USAGE));

        // floats and invalid symbols

        assertParseFailure(
                undoParser, "1.0", String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        assertParseFailure(
                undoParser, "a1", String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        // Overwhelmingly large numbers (long)

        assertParseFailure(
                undoParser, "1000000000000000", String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        // multiple arguments

        assertParseFailure(
                undoParser, "1 2", String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

    }
}
