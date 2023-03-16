package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Date;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.TaskDescription;

/**
 * Parses input arguments and creates a new DeadlineCommand object
 */
public class DeadlineCommandParser implements Parser<DeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE));
        }

        TaskDescription description = ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK).get());
        Date deadlineDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        DeadlineTask deadlineTask = new DeadlineTask(description, deadlineDate);

        return new DeadlineCommand(deadlineTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
