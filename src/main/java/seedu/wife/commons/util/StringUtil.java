package seedu.wife.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;
import static seedu.wife.commons.util.CollectionUtil.requireAllNonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;

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
     * Returns {@code true} if the {@code sentence} contains the {@code word}.
     * Case-insensitive and does not require the whole word to match
     * <br>examples:<pre>
     *       containsSubstringIgnoreCase("ABc def", "abc") == true
     *       containsSubstringIgnoreCase("ABc def", "DEF") == true
     *       containsSubstringIgnoreCase("ABc def", "AB") == true
     *       containsSubstringIgnoreCase("ABc def", "ghi") == false
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsSubstringIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence.toLowerCase();
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(name -> name.contains(preppedWord));
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
     * Returns true if {@code s} represents an integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NumberFormatException if {@code s} is not an integer.
     */
    public static boolean isInteger(String s) {
        requireNonNull(s);
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ne) {
            return false;
        }
        return true;
    }
    /**
     * Returns string with first letter of each word capitalized
     * e.g. capitalized name -> Capitalized Name
     */
    public static String capitalizeString(String s) {
        requireNonNull(s);
        String capitalizedString = s;
        String[] words = capitalizedString.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }

        capitalizedString = String.join(" ", words);
        return capitalizedString;
    }

    //@@author jnjy-reused
    //Reused from
    //https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/src/main/java/seedu/foodrem/commons/util/StringUtil.java
    //with minor modification
    /**
     * Returns index obtained from command keyed in by users.
     */
    public static Index getIndexFromCommand(String args, String commandHelpMessage) throws ParseException {
        requireAllNonNull(args, commandHelpMessage);

        String trimmedArgs = args.trim();

        if (trimmedArgs.isBlank()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    commandHelpMessage));
        }

        if (!StringUtil.isInteger(trimmedArgs)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    commandHelpMessage));
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedArgs)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_INVALID_INDEX));
        }

        try {
            return ParserUtil.parseIndex(trimmedArgs);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, commandHelpMessage));
        }
    }
    //@@author
}
