package ezschedule.logic.parser;

import java.util.Arrays;

import ezschedule.commons.core.Messages;
import ezschedule.logic.commands.EditCommand;
import ezschedule.logic.commands.FindCommand;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.event.Date;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.model.event.EventMatchesDatePredicate;
import ezschedule.model.event.Name;
import ezschedule.logic.commands.FindCommand.FindEventDescriptor;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATE);

        // TODO: update check
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

//        String[] nameKeywords = trimmedArgs.split("\\s+");
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            findEventDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            findEventDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get()));
        }

        return new FindCommand(findEventDescriptor);
    }
}
