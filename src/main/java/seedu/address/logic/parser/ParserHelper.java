package seedu.address.logic.parser;

import java.util.stream.Stream;

/**
 * Helper class that provides utility functions for checking of command formats
 */
public class ParserHelper {

    /**
     * Checks if desired prefixes are present in map of argument tokens
     * @param argumentMultimap Map of argument tokens
     * @param prefixes Prefixes to be checked for presence
     * @return Boolean indicating if all desired prefixes are present
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks the map of argument tokens if preamble (any text before the first valid prefix)
     * @param argumentMultimap Map of argument tokens
     * @return Boolean indicating if there is preamble present
     */
    public static boolean isPreambleEmpty(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getPreamble().isEmpty();
    }

}
