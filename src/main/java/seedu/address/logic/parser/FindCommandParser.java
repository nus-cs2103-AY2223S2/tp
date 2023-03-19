package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<attribute>\\S+)(?<keywords>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Nric nric;
        Name name;
        Address address;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_ADDRESS);

        if (isPrefixesPresent(argMultimap, PREFIX_NRIC)
                && argMultimap.getPreamble().isEmpty()) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
            String[] nrics = nric.toString().split("\\s+");
            return new FindCommand(new NricContainsKeywordsPredicate(Arrays.asList(nrics)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_NAME)
                && argMultimap.getPreamble().isEmpty())  {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            String[] names = name.toString().split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(names)));

        } else if (isPrefixesPresent(argMultimap, PREFIX_ADDRESS)
                && argMultimap.getPreamble().isEmpty()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            String[] addresses = address.toString().split("\\s+");
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addresses)));
        }
        else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Returns true if the given prefixes does not contain empty
     *  {@code Optional} values in the given {@code ArgumentMultimap}.
     */
    private static boolean isPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
