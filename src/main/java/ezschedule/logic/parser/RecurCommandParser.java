package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_EVERY;
import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

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
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_EVERY);

        Index index;
        Date endDate;
        RecurFactor recurFactor;

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_EVERY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE), pe);
        }

        endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        recurFactor = ParserUtil.parseRecurFactor(argMultimap.getValue(PREFIX_EVERY).get());

        return new RecurCommand(index, endDate, recurFactor);
    }
}
