package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Date;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.TaskDescription;

/**
 * Parses input arguments and creates a new DeadlineCommand object
 */
public class EventCommandParser implements Parser<EventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        TaskDescription description = ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK).get());
        Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        EventTask eventTask = new EventTask(description, startDate, endDate);

        return new EventCommand(eventTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}