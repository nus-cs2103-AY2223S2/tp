package ezschedule.logic.parser;

import java.util.stream.Stream;

import ezschedule.commons.core.Messages;
import ezschedule.logic.commands.FindCommand;
import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATE);

        if (!areAnyPrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

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
