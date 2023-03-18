package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.AppendCustomerNoteCommand;

public class AppendCustomerNoteCommandParserTest {
    private AppendCustomerNoteCommandParser parser = new AppendCustomerNoteCommandParser();
    private final String nonEmptyString = "Test Note";

    @Test
    public void parse_emptyString_success() {
        Index targetIndex = INDEX_FIRST_CUSTOMER;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        AppendCustomerNoteCommand expectedCommand = new AppendCustomerNoteCommand(INDEX_FIRST_CUSTOMER, "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_nonEmptyString_success() {
        Index targetIndex = INDEX_FIRST_CUSTOMER;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + nonEmptyString;
        AppendCustomerNoteCommand expectedCommand = new AppendCustomerNoteCommand(INDEX_FIRST_CUSTOMER, nonEmptyString);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendCustomerNoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AppendCustomerNoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AppendCustomerNoteCommand.COMMAND_WORD + " " + nonEmptyString, expectedMessage);
    }
}
