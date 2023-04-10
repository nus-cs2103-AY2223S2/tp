package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    private static final Logger logger = LogsCenter.getLogger(StringUtil.class);

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsKeyWordIgnoreCase("ABc def", "abc") == true
     *       containsKeyWordIgnoreCase("ABc def", "DEF") == true
     *       containsKeyWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsKeyWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence.toLowerCase();
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(existentWord -> existentWord.contains(preppedWord));
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


    /**
     * Saves the String input to the specified file.
     * Adds the String to the top of the file.
     * @param input cannot be null
     * @param filePath cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static void saveStringFile(String input, Path filePath) throws IOException {
        requireNonNull(input);
        requireNonNull(filePath);
        FileUtil.writeToFile(filePath, input, true);
    }

    /**
     * Reads the specified text file and saves it into an ArrayList.
     * @param filePath cannot be null
     * @return An arrayList of strings
     * @throws IOException If there was an error during reading from the fille
     */
    public static ArrayList<String> readStringFile(Path filePath) throws IOException {
        requireNonNull(filePath);
        if (!Files.exists(filePath)) {
            logger.info("Command file " + filePath + " not found");
            return new ArrayList<String>(100);
        }

        List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
        return new ArrayList<String>(lines);
    }
}
