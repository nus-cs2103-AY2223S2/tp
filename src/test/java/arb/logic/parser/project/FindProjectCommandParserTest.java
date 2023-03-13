package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.FindProjectCommand;
import arb.model.project.TitleContainsKeywordsPredicate;

public class FindProjectCommandParserTest {

    private FindProjectCommandParser parser = new FindProjectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindProjectCommand() {
        // no leading and trailing whitespaces
        FindProjectCommand expectedFindProjectCommand =
                new FindProjectCommand(new TitleContainsKeywordsPredicate(Arrays.asList("Sky", "Painting")));
        assertParseSuccess(parser, "Sky Painting", expectedFindProjectCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Sky \n \t Painting  \t", expectedFindProjectCommand);
    }

}
