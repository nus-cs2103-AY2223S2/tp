package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.model.task.ContentContainsKeywordsPredicate;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

/**
 * Tests {@code FindTaskCommandParser} on its behaviours on taking valid and invalid arguments.
 */
public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {
        String[] trimmed = ("key").split("\\s+");
        TitleContainsKeywordsPredicate tPred = new TitleContainsKeywordsPredicate(Arrays.asList(trimmed));
        ContentContainsKeywordsPredicate cPred = new ContentContainsKeywordsPredicate(Arrays.asList(trimmed));

        assertParseSuccess(parser, "key", new FindTaskCommand(tPred, cPred));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }
}
