package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;

import seedu.task.logic.commands.ScheduleCommand;
import seedu.task.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns a ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ScheduleCommand parse(String args) throws ParseException {
        try {
            LocalDate date = ParserUtil.parseLocalDate(args);
            return new ScheduleCommand(date);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }
    }
}
