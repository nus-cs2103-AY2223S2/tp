package ezschedule.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import ezschedule.commons.core.Messages;
import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.RecurCommand;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.event.Date;
import ezschedule.model.event.RecurFactor;

/**
 * Parses input arguments and creates a new RecurCommand object.
 */
public class RecurCommandParser implements Parser<RecurCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RecurCommand
     * and returns a RecurCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecurCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_DATE, CliSyntax.PREFIX_EVERY);

        Index index;
        Date endDate;
        RecurFactor recurFactor;

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATE, CliSyntax.PREFIX_EVERY)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    RecurCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE), pe);
        }

        endDate = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get());
        recurFactor = ParserUtil.parseRecurFactor(argMultimap.getValue(CliSyntax.PREFIX_EVERY).get());

        return new RecurCommand(index, endDate, recurFactor);
    }
}
