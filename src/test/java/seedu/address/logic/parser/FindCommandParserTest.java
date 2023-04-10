package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.application.InternshipStatus.PENDING;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindDateCommand;
import seedu.address.logic.commands.FindStatusCommand;
import seedu.address.model.application.AfterDatePredicate;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.StatusPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindStatusCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindStatusCommand =
                new FindStatusCommand(new StatusPredicate(PENDING));
        assertParseSuccess(parser, " s/PENDING", expectedFindStatusCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " s/   PENDING \t ", expectedFindStatusCommand);
    }

    @Test
    public void parse_validArgs_returnsFindDateCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindDateCommand =
                new FindDateCommand(new AfterDatePredicate(new InterviewDate("2023-03-30 12:00 AM")));
        assertParseSuccess(parser, " after/2023-03-30 12:00 AM", expectedFindDateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " after/    \t 2023-03-30 12:00 AM \t ", expectedFindDateCommand);
    }

}
