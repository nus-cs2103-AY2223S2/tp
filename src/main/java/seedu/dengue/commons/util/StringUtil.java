package seedu.dengue.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

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
        checkArgument(preppedWord.split("\\s+")
                .length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code substring}.
     * Ignores case, and a full word match is not required.
     * @param sentence cannot be null
     * @param substring cannot be null
     */
    public static boolean containsSubstringIgnoreCase(String sentence, String substring) {
        Pattern pattern = Pattern.compile(substring, Pattern.CASE_INSENSITIVE);
        requireNonNull(sentence);
        requireNonNull(substring);
        String preppedString = substring.trim();

        checkArgument(!preppedString.isEmpty(), "String parameter cannot be empty");
        checkArgument(preppedString
                .split("\\s+").length == 1, "String parameter should be a single character");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence).anyMatch(w -> pattern.matcher(w).find());
    }

    /**
     * Returns true if the {@code sentence} contains the {@code substring} substring can be more than one word.
     * Ignores case, and a full word match is not required.
     * @param sentence cannot be null
     * @param substring cannot be null
     */
    public static boolean containsSubstringWithLengthMoreThanOneIgnoreCase(String sentence, String substring) {
        Pattern pattern = Pattern.compile(substring, Pattern.CASE_INSENSITIVE);
        requireNonNull(sentence);
        requireNonNull(substring);
        String preppedString = substring.trim();

        checkArgument(!preppedString.isEmpty(), "String parameter cannot be empty");
        Predicate<String> asPredicate = pattern.asPredicate();

        return asPredicate.test(sentence);
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
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
