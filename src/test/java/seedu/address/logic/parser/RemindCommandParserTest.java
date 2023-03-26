package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTimes.CLOCK_FIXED_AT_TIME_NOW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.model.event.StartTimeWithinDaysPredicate;

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
