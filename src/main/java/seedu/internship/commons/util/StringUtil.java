package seedu.internship.commons.util;

import seedu.internship.logic.parser.ParserUtil;
import seedu.internship.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code input} equals the {@code target}.
     *   Ignores case but both strings must match.
     *   <br>examples:<pre>
     *       matchingStringIgnoreCase("abc def", "abc def") == true
     *       matchingStringIgnoreCase("ABc dEf", "abc DEF") == true
     *       matchingStringIgnoreCase("ABc def", "AB") == false //not a full string match
     *       </pre>
     * @param input cannot be null
     * @param target cannot be null, cannot be empty
     */
    public static boolean matchingStringIgnoreCase(String input, String target) {
        requireNonNull(input);
        requireNonNull(target);

        checkArgument(!target.isEmpty(), "Target cannot be empty!");
        assert(!target.isEmpty());

        return target.equalsIgnoreCase(input);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Throws ParseException objects with error message {@code Messages.MESSAGE_INVALID_COMMAND_FORMAT} if {@code s}
     *   does not represent an integer or represents a positive integer with the plus sign "+" in front and
     *   {@code Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX} if {@code s} represents a negative integer. Else,
     *   does nothing.
     * @param s String input
     * @throws ParseException with error message {@code Messages.MESSAGE_INVALID_COMMAND_FORMAT} if {@code s}
     *   does not represent an integer or represents a positive integer with the plus sign "+" in front and
     *   {@code Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX} if {@code s} represents a negative integer.
     */
    public static void nonZeroUnsignedIntegerCheck(String s) throws ParseException {
        requireNonNull(s);

        int value;

        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_INDEX_FORMAT);
        }

        if (s.startsWith("+")) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_POSITIVE_SIGNED_INDEX);
        }

        if (value <= 0) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_INDEX);
        }

    }
}
