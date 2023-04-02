package seedu.address.commons.util;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.parser.ArgumentMultimap;
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

    public static boolean checkIfContainsInvalidPrefixes(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getAllValues().stream().parallel().anyMatch(arr -> arr.get(0).contains("/"));
    }

    public static boolean hasNonEmptyPreamble(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .noneMatch(prefix -> prefix.getPrefix()
                        .startsWith(argumentMultimap.getPreamble()));
    }
}
