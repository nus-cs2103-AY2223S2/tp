package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReviewCommand;
import seedu.address.model.person.NameContainsExactKeywordsPredicate;


public class ReviewCommandParserTest {

    private ReviewCommandParser parser = new ReviewCommandParser();

    @Test
    public void parse_empty_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsReviewTaskCommand() {
        // no leading and trailing whitespaces
        ReviewCommand expectedReviewCommand =
                new ReviewCommand(new NameContainsExactKeywordsPredicate(((Arrays.asList("Alice", "Bob")))));
        assertParseSuccess(parser, "Alice Bob", expectedReviewCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedReviewCommand);
    }
}
