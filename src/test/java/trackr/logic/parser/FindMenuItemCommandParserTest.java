package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.menu.FindMenuItemCommand;
import trackr.logic.parser.menu.FindMenuItemCommandParser;
import trackr.model.menu.ItemNameContainsKeywordsPredicate;

public class FindMenuItemCommandParserTest {

    private FindMenuItemCommandParser parser = new FindMenuItemCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMenuItemCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindMenuItemCommand expectedFindCommand =
                new FindMenuItemCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList("Nike", "Cupcake")));
        assertParseSuccess(parser, "Nike Cupcake", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Nike \n \t Cupcake  \t", expectedFindCommand);
    }

}
