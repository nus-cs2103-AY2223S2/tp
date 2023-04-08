package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import seedu.sprint.logic.commands.FindCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindApplicationCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindApplicationCommand
     * and returns a FindApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS)) {
            String[] keywords = trimmedArgs.split("\\s+");
            return new FindCommand(new ApplicationContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        Prefix[] prefixArray = {PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS};

        boolean definedFormat = false;

        for (Prefix prefix : prefixArray) {
            if (trimmedArgs.substring(0, 2).equals(prefix.toString())) {
                definedFormat = true;
                break;
            }
        }

        if (!definedFormat) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        HashMap<Prefix, List<String>> map = new HashMap<>();

        for (Prefix prefix : prefixArray) {
            if (arePrefixesPresent(argMultimap, prefix)) {
                List<String> keywordList = getValues(argMultimap, prefix);
                String[] keywordArray = keywordList.get(0).split(" ");
                for (String keyword: keywordArray) {
                    keyword.trim();
                }

                if (keywordArray != null) {
                    map.put(prefix, Arrays.asList(keywordArray));
                }
            }
        }
        return new FindCommand(new ApplicationContainsKeywordsPredicate(map));
    }


    /**
     * Returns all the user-inputted strings for the given prefix.
     * @param argMultimap An argument multimap.
     * @param prefix A prefix.
     * @return A list of string(s) for the prefix made by user input.
     */
    public List<String> getValues(ArgumentMultimap argMultimap, Prefix prefix) {
        return argMultimap.getAllValues(prefix);
    }

    /**
     * Checks if there are any prefixes in the user input.
     * @param argumentMultimap An argument multimap.
     * @param prefixes A number of prefixes.
     * @return if any of the prefix in as key in argumentMultimap has non-null value.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
