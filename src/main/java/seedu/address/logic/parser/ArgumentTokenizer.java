package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix>value <prefix>value ...}<br>
 *     e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July}  where prefixes are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code t/}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    private static final String WHITESPACE_OR_STRING_END_REGEX = "( |$)";

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with
     * @return           ArgumentMultimap object that maps prefixes to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) {
        List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
        return extractArguments(argsString, positions);
    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to find in the arguments string
     * @return           List of zero-based prefix positions in the given arguments string
     */
    private static List<PrefixPosition> findAllPrefixPositions(String argsString, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(prefix -> findPrefixPositions(argsString, prefix).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllPrefixPositions}
     */
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();

        int prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), 0);
        while (prefixPosition != -1) {
            PrefixPosition extendedPrefix = new PrefixPosition(prefix, prefixPosition);
            positions.add(extendedPrefix);
            prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), prefixPosition);
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if:<p>
     *
     * (1) there is a whitespace before and after {@code prefix}; or<p>
     *
     * (2) there is a whitespace before {@code prefix} and the occurrence is
     * at the end of {@code argsString}.<p>
     *
     * Returns -1 if no such occurrence can be found.<p>
     *
     * E.g. Suppose {@code prefix} = "/bar" and {@code fromIndex} = 0. If
     * {@code argsString} = "/foo 1/bar 2", this method returns -1 as
     * there are no valid occurrences of "/bar" with whitespace before it.
     * However, if {@code argsString} = "/foo 1 /bar 2" or
     * {@code argsString} = "/foo 1 /bar", this method returns 7.
     */
    private static int findPrefixPosition(String argsString, String prefix, int fromIndex) {
        int prefixIndex = findPrefixPositionWithWhitespaceBeforeAndAfter(argsString, prefix, fromIndex);

        if (prefixIndex == -1) {
            prefixIndex = findPrefixPositionAtEndOfString(argsString, prefix, fromIndex);
        }

        return prefixIndex == -1 ? -1
                : prefixIndex + 1; // +1 as offset for whitespace
    }

    /**
     * Returns the index of the first occurrence of {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before and after {@code prefix}.<p>
     *
     * Returns -1 if no such occurrence can be found.<p>
     *
     * E.g. Suppose {@code prefix} = "/bar" and {@code fromIndex} = 0. If
     * {@code argsString} = "/foo 1/bar 2" or
     * {@code argsString} = "/foo 1 /bar2", this method returns -1 as
     * there are no valid occurrences of "/bar" with whitespace before and
     * after it. However, if {@code argsString} = "/foo 1 /bar 2" , this
     * method returns 7.
     */
    private static int findPrefixPositionWithWhitespaceBeforeAndAfter(String argsString, String prefix, int fromIndex) {
        return argsString.indexOf(" " + prefix + " ", fromIndex);
    }

    /**
     * Returns the index of the occurrence of {@code prefix} which occurs
     * at the end of the {@code argsString}. An occurrence is valid if
     * there is a whitespace before {@code prefix}.<p>
     *
     * Returns -1 if no such occurrence can be found.<p>
     *
     * E.g. Suppose {@code prefix} = "/bar" and {@code fromIndex} = 0. If
     * {@code argsString} = "/foo 1/bar" or
     * {@code argsString} = "/foo 1 /bar 2", this method returns -1 as
     * there are no valid occurrences of "/bar" which occurs at the end of
     * {@code argsString} with whitespace before it. However, if
     * {@code argsString} = "/foo 1 /bar", this method returns 7.
     */
    private static int findPrefixPositionAtEndOfString(String argsString, String prefix, int fromIndex) {
        String toMatch = " " + prefix;

        int minLength = toMatch.length();
        int matchableLength = argsString.length() - fromIndex;

        if (matchableLength < minLength) {
            return -1;
        }

        int substringToCheckBeginIndex = argsString.length() - minLength;
        String toCheck = argsString.substring(substringToCheckBeginIndex);

        return toCheck.equals(toMatch) ? substringToCheckBeginIndex : -1;
    }

    /**
     * Extracts prefixes and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}
     * @return                ArgumentMultimap object that maps prefixes to their arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<PrefixPosition> prefixPositions) {

        // Sort by start position
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getStartPosition() - prefix2.getStartPosition());

        // Insert a PrefixPosition to represent the preamble
        PrefixPosition preambleMarker = new PrefixPosition(new Prefix(""), 0);
        prefixPositions.add(0, preambleMarker);

        // Add a dummy PrefixPosition to represent the end of the string
        PrefixPosition endPositionMarker = new PrefixPosition(new Prefix(""), argsString.length());
        prefixPositions.add(endPositionMarker);

        // Map prefixes to their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(argPrefix, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition} or the first character of a valid
     * prefix (detected by a match with {@code Prefix#VALIDATION_REGEX}) whichever comes first.
     */
    private static String extractArgumentValue(String argsString,
                                        PrefixPosition currentPrefixPosition,
                                        PrefixPosition nextPrefixPosition) {
        Prefix prefix = currentPrefixPosition.getPrefix();

        int valueStartPos = currentPrefixPosition.getStartPosition() + prefix.getPrefix().length();
        String value = argsString.substring(valueStartPos, nextPrefixPosition.getStartPosition());

        value = removeArgumentsFromValue(value);

        return value.trim();
    }

    /**
     * Returns a substring of {@code value} containing all characters up to the first character of a valid prefix.
     */
    private static String removeArgumentsFromValue(String value) {
        String[] substrs = value.split(" " + Prefix.VALIDATION_REGEX + WHITESPACE_OR_STRING_END_REGEX);
        return substrs.length == 0 ? "" : substrs[0];
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    private static class PrefixPosition {
        private int startPosition;
        private final Prefix prefix;

        PrefixPosition(Prefix prefix, int startPosition) {
            this.prefix = prefix;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Prefix getPrefix() {
            return prefix;
        }
    }

}
