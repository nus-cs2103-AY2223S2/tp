package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.FindCommand;
import ezschedule.model.event.EventContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new EventContainsKeywordsPredicate(Arrays.asList("A", "B")));
        assertParseSuccess(parser, "A B", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n A \n \t B  \t", expectedFindCommand);
    }
}
