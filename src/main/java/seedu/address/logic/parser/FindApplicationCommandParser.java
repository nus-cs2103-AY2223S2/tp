package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindApplicationCommand object
 */
public class FindApplicationCommandParser implements ApplicationParser<FindApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindApplicationCommand
     * and returns a FindApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindApplicationCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindApplicationCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS)) {
            String[] keywords = trimmedArgs.split("\\s+");
            return new FindApplicationCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        Prefix[] prefixArray = {PREFIX_ROLE, PREFIX_COMPANY_NAME, PREFIX_STATUS};
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
        return new FindApplicationCommand(new NameContainsKeywordsPredicate(map));
    }


    /**
     * @param argMultimap An argument multimap.
     * @param prefix A prefix.
     * @return A list of string for the prefix made by user input.
     */
    public List<String> getValues(ArgumentMultimap argMultimap, Prefix prefix) {
        return argMultimap.getAllValues(prefix);
    }

    /**
     * @param argumentMultimap An argument multimap.
     * @param prefixes A number of prefixes.
     * @return if any of the prefix in as key in argumentMultimap has non-null value.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
