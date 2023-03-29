package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseFailure;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import teambuilder.logic.commands.ShowCommand;
import teambuilder.model.person.TeamContainsKeywordsPredicate;

class ShowCommandParserTest {
    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ShowCommand expectedFindCommand =
                new ShowCommand(new TeamContainsKeywordsPredicate(Arrays.asList("TeamA", "TeamB")));
        assertParseSuccess(parser, "TeamA TeamB", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n TeamA \n \t TeamB  \t", expectedFindCommand);
    }
}