package vimification.common.util;

import static java.util.Objects.requireNonNull;

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
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    public static String requireNonEmpty(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return s;
    }

    public static String requireNonEmpty(String s, String message) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return s;
    }
}
