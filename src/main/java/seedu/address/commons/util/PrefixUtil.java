package seedu.address.commons.util;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;

/**
 * Helper functions for handling prefixes.
 */
public class PrefixUtil {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap Mapping of prefix to arguments.
     * @param prefixes Prefixes to be checked if present.
     * @return True If provided prefixes are present and false otherwise.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the values in the given ArgumentMultimap contain an invalid prefix.
     *
     * @param argumentMultimap the ArgumentMultimap to check for invalid prefixes
     * @return true if any of the values in the ArgumentMultimap contain an invalid prefix, false otherwise
     */
    public static boolean checkIfContainsInvalidPrefixes(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getAllEntries().stream().parallel().anyMatch(entry -> {
            Prefix prefix = entry.getKey();
            if (prefix == CliSyntax.PREFIX_ADDRESS) {
                return false;
            }
            return entry.getValue().stream().anyMatch(s -> s.contains("/"));
        });
    }

    /**
     * Returns true if the given ArgumentMultimap has a non-empty preamble that does not start
     * with any of the specified prefixes.
     *
     * @param argumentMultimap the ArgumentMultimap to check for a non-empty preamble
     * @param prefixes the prefixes to check for at the start of the preamble
     * @return true if non-empty preamble that does not start with any of the specified prefixes, false otherwise
     */
    public static boolean hasNonEmptyPreamble(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .noneMatch(prefix -> prefix.getPrefix().startsWith(argumentMultimap.getPreamble()));
    }
}
