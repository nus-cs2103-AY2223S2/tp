package seedu.wife.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import seedu.wife.commons.core.HelpMenu;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.HelpCommand;
import seedu.wife.model.food.NameContainsKeywordsPredicate;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_invalidArg_returnsInvalidArgMessage() {
        //invalid string
//        Command command = parser.parse("sdkjksjks");

//        assertEquals(expectedCommand, command);
        assertParseFailure(parser, "sdkjksjks", HelpMenu.INVALID.getCommandUsage());
    }

//    @Test
//    public void parse_validArgs_returnsFindCommand() {
//        // no leading and trailing whitespaces
//        FindCommand expectedFindCommand =
//                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Meiji", "Chocolate")));
//        assertParseSuccess(parser, "Meiji Chocolate", expectedFindCommand);
//
//        // multiple whitespaces between keywords
//        assertParseSuccess(parser, " \n Meiji \n \t Chocolate  \t", expectedFindCommand);
//    }
}
