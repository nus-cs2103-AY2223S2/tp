package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_BUY_FLOUR;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.FindTaskCommand;
import trackr.model.task.TaskContainsKeywordsPredicate;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {
        // no leading and trailing whitespaces
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(Arrays.asList("Buy", "Flour"));
        FindTaskCommand expectedFindTaskCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, TASK_NAME_DESC_BUY_FLOUR, expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "\n Buy \n \t Flour  \t", expectedFindTaskCommand);
    }

}
