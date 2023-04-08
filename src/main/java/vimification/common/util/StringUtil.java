package vimification.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    // /**
    // * Returns true if the {@code sentence} contains the {@code word}. Ignores case, but a full
    // word
    // * match is required.
    // * <p>
    // * For examples:
    // *
    // * <pre>
    // * containsWordIgnoreCase("ABc def", "abc") == true
    // * containsWordIgnoreCase("ABc def", "DEF") == true
    // * containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
    // * </pre>
    // *
    // * @param sentence cannot be null
    // * @param word cannot be null, cannot be empty, must be a single word
    // */
    // public static boolean containsWordIgnoreCase(String sentence, String word) {
    // requireNonNull(sentence);
    // requireNonNull(word);

    // String preppedWord = word.strip();
    // checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
    // checkArgument(preppedWord.split("\\s+").length == 1,
    // "Word parameter should be a single word");

    // String preppedSentence = sentence;
    // String[] wordsInPreppedSentence = preppedSentence.split("\\s+");
    // return Arrays.stream(wordsInPreppedSentence).anyMatch(preppedWord::equalsIgnoreCase);
    // }

    /**
     * Returns a detailed message of the {@link Throwable}, including the stack trace.
     *
     * @param t the {@code Throwable} to get the information from
     * @return a string contains the message and the stack trace information
     */
    public static String getDetails(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Checks that the specified string is not empty. This method is designed primarily for doing
     * parameter validation in methods and constructors.
     *
     * @param str the string to check for emptiness
     * @return {@code str} if not empty
     * @throws IllegalArgumentException if {@code str} is empty
     */
    public static String requireNonEmpty(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return str;
    }

    /**
     * Checks that the specified string is not empty and throws a customized
     * {@link IllegalArgumentException} if it is. This method is designed primarily for doing
     * parameter validation in methods and constructors with multiple parameters.
     *
     * @param str the string to check for emptiness
     * @param msg detail message to be used in the event that {@code str} is empty
     * @return {@code str} if not empty
     * @throws IllegalArgumentException if {@code str} is empty
     */
    public static String requireNonEmpty(String str, String msg) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException(msg);
        }
        return str;
    }
}
