package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.ScheduleCommand;
import seedu.task.model.task.Effort;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_haveDateNoEffort_returnsScheduleCommand() {
        assertParseSuccess(parser, " D/2023-07-18", new ScheduleCommand(LocalDate.parse("2023-07-18")));
    }

    @Test
    public void parse_haveDateHaveEffort_returnsScheduleCommand() {
        assertParseSuccess(parser, " D/2023-07-18 E/10", new ScheduleCommand(LocalDate.parse("2023-07-18"),
                new Effort(10)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }
}
