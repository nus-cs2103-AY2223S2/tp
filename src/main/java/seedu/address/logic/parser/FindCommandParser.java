package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String keyWords = "";
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, Prefix.NAME, Prefix.PHONE, Prefix.EMAIL,
                        Prefix.STATION, Prefix.TELEGRAM_HANDLE,
                        Prefix.GROUP_TAG, Prefix.MODULE_TAG);
        if (!atLeastOnePrefixPresent(argMultimap, Prefix.NAME, Prefix.STATION, Prefix.PHONE, Prefix.EMAIL,
                Prefix.TELEGRAM_HANDLE,
                Prefix.GROUP_TAG, Prefix.MODULE_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Prefix> prefixPresent = findPrefixPresent(argMultimap, Prefix.NAME, Prefix.STATION, Prefix.PHONE,
                Prefix.EMAIL,
                Prefix.TELEGRAM_HANDLE,
                Prefix.GROUP_TAG,
                Prefix.MODULE_TAG);

        Predicate<Person> predicate = null;
        for (Prefix prefix : prefixPresent) {
            if (argMultimap.getValue(prefix).isPresent()) {
                keyWords = argMultimap.getValue(prefix).get();
            }

            String[] keyWordArr = trimArgs(keyWords);
            if (predicate == null) {
                predicate = new ContainsKeywordsPredicate(Arrays.asList(keyWordArr), prefix);
            } else {
                predicate = predicate.and(new ContainsKeywordsPredicate(Arrays.asList(keyWordArr), prefix));
            }
        }


        return new FindCommand(predicate);
    }

    private String[] trimArgs(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return trimmedArgs.split("\\s+");
    }

    private static boolean atLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long count = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count();
        return count >= 1;
    }

    private static List<Prefix> findPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
    }

}

