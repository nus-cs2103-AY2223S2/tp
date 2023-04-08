package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.AppendOrderNoteCommand;

public class AppendOrderNoteCommandParserTest {
    private AppendOrderNoteCommandParser parser = new AppendOrderNoteCommandParser();
    private final String nonEmptyString = "Test Note";

    @Test
    public void parse_emptyString_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        AppendOrderNoteCommand expectedCommand = new AppendOrderNoteCommand(INDEX_FIRST, "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_nonEmptyString_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + nonEmptyString;
        AppendOrderNoteCommand expectedCommand = new AppendOrderNoteCommand(INDEX_FIRST, nonEmptyString);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendOrderNoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AppendOrderNoteCommand.COMMAND_WORD, expectedMessage);

        // no note
        assertParseFailure(parser, AppendOrderNoteCommand.COMMAND_WORD + " 1" + nonEmptyString, expectedMessage);
    }
}
