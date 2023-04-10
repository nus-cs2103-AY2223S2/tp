package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    public static final String NEWLINE = System.lineSeparator();
    private static final List<Character> WS = List.of(' ',
            '\n',
            '\t',
            '\r');

    private StringUtil() {
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

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
        return t.getMessage() + "\n" + sw;
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
     * Splits string into list of strings by whitespace.
     *
     * @param str String to be split
     * @return List of strings
     */
    public static List<String> splitWhitespace(String str) {
        List<String> res = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (WS.contains(c) && !temp.isEmpty()) {
                res.add(temp);
                temp = "";
            } else if (!WS.contains(c)) {
                temp += c;
            }
        }
        if (!temp.isEmpty()) {
            res.add(temp);
        }
        return res;
    }

    /**
     * Clears whitespace at beginning and end of string
     *
     * @param str String to process
     * @return Resultant string
     */
    public static String cleanup(String str) {
        int start = 0;
        int end = str.length();
        while (start < str.length() && WS.contains(str.charAt(start))) {
            ++start;
        }
        while (end > 0 && WS.contains(str.charAt(end - 1))) {
            --end;
        }
        if (start > end) {
            return "";
        }

        return str.substring(start,
                end);
    }

    /**
     * Fuses list of characters into a single string
     *
     * @param s List of characters
     * @return Resultant string
     */
    public static String listToString(List<Character> s) {
        return s.stream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + b);
    }

    /**
     * Joins strings using a specified delimiter
     *
     * @param delimiter Delimiter string
     * @param lines     Strings to join
     * @return Joined string
     */
    public static String join(String delimiter, String... lines) {
        return Stream.of(lines)
                .reduce("", (a, b) -> a + delimiter + b)
                .substring(delimiter.length());
    }

    /**
     * Multiline String indentation
     *
     * @param string Lines to be indented
     * @param spaces Amount of spaces to indent with
     * @return Indented multiline string
     */
    public static String indent(String string, int spaces) {
        String prepend = Stream.generate(() -> " ")
                .limit(spaces)
                .reduce("", (a, b) -> a + b);
        if (string.length() == 0) {
            return prepend;
        }
        return string.lines()
                .map(s -> prepend + s)
                .reduce("", (a, b) -> a + NEWLINE + b)
                .substring(1);
    }
}
