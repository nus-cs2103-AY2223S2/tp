package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;

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
                        Prefix.ADDRESS, Prefix.TELEGRAM_HANDLE,
                        Prefix.GROUP_TAG, Prefix.MODULE_TAG);
        if (!onePrefixPresent(argMultimap, Prefix.NAME, Prefix.ADDRESS, Prefix.PHONE, Prefix.EMAIL,
                Prefix.TELEGRAM_HANDLE,
                Prefix.GROUP_TAG, Prefix.MODULE_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Prefix prefixPresent = findPrefixPresent(argMultimap, Prefix.NAME, Prefix.ADDRESS, Prefix.PHONE, Prefix.EMAIL,
                Prefix.TELEGRAM_HANDLE,
                Prefix.GROUP_TAG, Prefix.MODULE_TAG);
        if (argMultimap.getValue(prefixPresent).isPresent()) {
            keyWords = argMultimap.getValue(prefixPresent).get();
        }
        String[] keyWordArr = trimArgs(keyWords);

        return new FindCommand(new ContainsKeywordsPredicate(Arrays.asList(keyWordArr), prefixPresent));
    }

    private String[] trimArgs(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return trimmedArgs.split("\\s+");
    }

    private static boolean onePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long count = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count();
        return count == 1;
    }

    private static Prefix findPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .findFirst()
                .orElse(null);
    }

}

