package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.FindRoutineCommand;
import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;

public class FindRoutineCommandParserTest {

    private FindRoutineCommandParser parser = new FindRoutineCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindRoutineCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindRoutineCommand expectedFindRoutineCommand =
                new FindRoutineCommand(new RoutineNameContainsKeywordsPredicate(Arrays.asList("Jumps", "Strength")));
        assertParseSuccess(parser, "Jumps Strength", expectedFindRoutineCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Jumps \n \t Strength  \t", expectedFindRoutineCommand);
    }

}
