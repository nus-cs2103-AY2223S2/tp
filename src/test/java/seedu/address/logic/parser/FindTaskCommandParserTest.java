package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccessNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

public class FindTaskCommandParserTest {

    private final FindTaskCommandParser parser = new FindTaskCommandParser();
    @Test
    public void parse_empty_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindTaskCommand =
                new FindTaskCommand(new TitleContainsKeywordsPredicate("Task 1"));
        assertParseSuccess(parser, "Task 1", expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccessNotEquals(parser, " \n Task \n \t 1  \t", expectedFindTaskCommand);
    }

}
