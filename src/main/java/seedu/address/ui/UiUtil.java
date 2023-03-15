package seedu.address.ui;

/**
 * A class that contains the UI-related constants and utility methods.
 */
public class UiUtil {
    private static final String LINE_BREAK = System.lineSeparator();
    private static final String INDENT = "            ";
    private static final String SPACE = " ";

    /**
     * Adds line breaks to a string so that each line is at most n characters long.
     *
     * @param text The string to be formatted.
     * @param n The maximum number of characters per line.
     * @return The formatted string.
     */
    public static String addLineBreaksWithIndent(String text, int n) {
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();

        int lineLength = 0;
        for (String word : words) {
            if (lineLength + word.length() > n) {
                sb.append(LINE_BREAK);
                sb.append(INDENT);
                sb.append(word);
                lineLength = 0;
            } else {
                sb.append(SPACE);
                sb.append(word);
                lineLength += word.length() + 1;
            }
        }
        return sb.toString();
    }

    /**
     * Adds line breaks to a string so that each line is at most n characters long.
     *
     * @param text The string to be formatted.
     * @param n The maximum number of characters per line.
     * @return The formatted string.
     */
    public static String addLineBreaksWithoutIndent(String text, int n) {
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();

        int lineLength = 0;
        for (String word : words) {
            if (lineLength + word.length() > n) {
                sb.append(LINE_BREAK);
                sb.append(word);
                lineLength = 0;
            } else {
                sb.append(SPACE);
                sb.append(word);
                lineLength += word.length() + 1;
            }
        }
        return sb.toString();
    }
}
