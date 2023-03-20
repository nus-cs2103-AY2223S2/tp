package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.model.event.StartTimeWithinDaysPredicate;

public class RemindCommandParserTest {

    // March 1 2023, 00:00
    private LocalDateTime timeNow = LocalDateTime.of(
            2023, Month.MARCH, 1, 0, 0);
    private RemindCommandParser parser = new RemindCommandParser(timeNow);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRemindCommand() {
        // no leading and trailing whitespaces
        RemindCommand expectedRemindCommand =
                new RemindCommand(new StartTimeWithinDaysPredicate(timeNow, 3));
        assertParseSuccess(parser, "3", expectedRemindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }
}
