package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.task.logic.parser.CliSyntax.PREFIX_EFFORT;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.task.logic.commands.ScheduleCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.task.Effort;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns a ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ScheduleCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_EFFORT);
            if (!arePrefixesPresent(argMultimap, PREFIX_DATE) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
            }

            LocalDate date = ParserUtil.parseLocalDate(argMultimap.getValue(PREFIX_DATE).get());

            if (!arePrefixesPresent(argMultimap, PREFIX_EFFORT)) {
                return new ScheduleCommand(date);
            } else {
                Effort effort = ParserUtil.parseEffort(argMultimap.getValue(PREFIX_EFFORT).get());
                return new ScheduleCommand(date, effort);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }
    }
}
