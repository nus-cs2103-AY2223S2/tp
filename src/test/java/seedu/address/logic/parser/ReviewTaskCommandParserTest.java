package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReviewTaskCommand;
import seedu.address.model.task.TitleContainsExactKeywordsPredicate;

public class ReviewTaskCommandParserTest {

    private final ReviewTaskCommandParser parser = new ReviewTaskCommandParser();
    @Test
    public void parse_empty_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviewTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsReviewTaskCommand() {
        // no leading and trailing whitespaces
        ReviewTaskCommand expectedReviewTaskCommand =
                new ReviewTaskCommand(new TitleContainsExactKeywordsPredicate((Arrays.asList("Task", "1"))));
        assertParseSuccess(parser, "Task 1", expectedReviewTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Task \n \t 1  \t", expectedReviewTaskCommand);
    }

}
