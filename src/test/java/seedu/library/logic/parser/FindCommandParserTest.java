package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.FindCommand;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;

public class FindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for title
        FindCommand expectedFindCommand =
                new FindCommand(new BookmarkContainsKeywordsPredicate(
                        Arrays.asList("Alice", "Bob"), null, null, null));
        assertParseSuccess(parser, " n/ Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords for title
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);

        // valid genre
        expectedFindCommand = new FindCommand(new BookmarkContainsKeywordsPredicate(
                null, Arrays.asList("Fantasy"), null, null));
        assertParseSuccess(parser, " g/ Fantasy", expectedFindCommand);

        // valid tag
        expectedFindCommand = new FindCommand(new BookmarkContainsKeywordsPredicate(
                null, null, Arrays.asList("Gore"), null));
        assertParseSuccess(parser, " t/ Gore", expectedFindCommand);

        // no leading and trailing whitespaces for title
        expectedFindCommand = new FindCommand(new BookmarkContainsKeywordsPredicate(
                        null, null, null, Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " a/ Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords for title
        assertParseSuccess(parser, " a/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "Alice", MESSAGE_INVALID_FORMAT);

        // invalid prefix used
        assertParseFailure(parser, "u/ Alice", MESSAGE_INVALID_FORMAT);
    }

}
