package seedu.event.logic.parser;

import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.event.testutil.TypicalTimes.CLOCK_FIXED_AT_TIME_NOW;

import org.junit.jupiter.api.Test;

import seedu.event.logic.commands.RemindCommand;
import seedu.event.model.event.StartTimeWithinDaysPredicate;

public class RemindCommandParserTest {


    private RemindCommandParser parser = new RemindCommandParser(CLOCK_FIXED_AT_TIME_NOW);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRemindCommand() {
        // no leading and trailing whitespaces
        RemindCommand expectedRemindCommand =
                new RemindCommand(new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, 3));
        assertParseSuccess(parser, "3", expectedRemindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }
}
