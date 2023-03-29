package ezschedule.logic.parser;

import static java.util.Objects.requireNonNull;

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

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RecurCommand.MESSAGE_USAGE),
                    pe);
        }

        Date endDate = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get());
        RecurFactor recurFactor = ParserUtil.parseRecurFactor(argMultimap.getValue(CliSyntax.PREFIX_EVERY).get());

        return new RecurCommand(index, endDate, recurFactor);
    }
}
