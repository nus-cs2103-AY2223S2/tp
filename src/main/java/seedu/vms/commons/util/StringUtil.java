package seedu.vms.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    /**
     * Checks if the given text contains all the character sequence in the
     * given list of patterns. The order of the pattern given is also checked
     * for but the case does not matter.
     *
     * @param text - the text to test.
     * @param patterns - the patterns to search for.
     * @return {@code true} if the given text matches the given list of
     *         patterns and {@code false} otherwise or patterns are empty.
     */
    public static boolean isMatching(String text, Collection<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        return text.toUpperCase().matches(compilePattern(patterns));
    }

    private static String compilePattern(Collection<String> patterns) {
        StringBuilder builder = new StringBuilder();
        for (String pattern : patterns) {
            builder.append(".*");
            builder.append(convertRegexChars(pattern));
        }
        builder.append(".*");
        return builder.toString();
    }

    private static String convertRegexChars(String pattern) {
        return pattern
                .toUpperCase()
                .replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\\[", "\\\\[")
                .replaceAll("\\]", "\\\\]")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\}", "\\\\}")
                .replaceAll("\\<", "\\\\<")
                .replaceAll("\\>", "\\\\>")
                .replaceAll("\\+", "\\\\+")
                .replaceAll("\\-", "\\\\-")
                .replaceAll("\\|", "\\\\|")
                .replaceAll("\\?", "\\\\?")
                .replaceAll("\\*", "\\\\*")
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\^", "\\\\^")
                .replaceAll("\\$", "\\\\$")
                .replaceAll("\\&", "\\\\&")
                .replaceAll("\\!", "\\\\!")
                .replaceAll("\\=", "\\\\=")
                .replaceAll("\\:", "\\\\:");
    }

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
     *
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

    /**
     * Formats the error messages in a nice string
     *
     * @param errMessages
     * @param formatInvalidMessage
     * @return formatted Strings
     */
    public static String formatErrorMessage(List<String> errMessages, String formatInvalidMessage) {
        final String formatConstrain = "- %s\n";

        StringBuilder builder = new StringBuilder();

        for (String message : errMessages) {
            builder.append(String.format(formatConstrain, message));
        }

        return String.format(formatInvalidMessage, builder.toString());
    }


    /**
     * Formats the given collection of appointment data.
     */
    public static String formatAppointmentListing(Collection<IdData<Appointment>> appts) {
        StringBuilder builder = new StringBuilder();

        for (IdData<Appointment> appt : appts) {
            builder.append(String.format("\n- #%04d", appt.getId() + 1));
        }

        return builder.toString();
    }

}
