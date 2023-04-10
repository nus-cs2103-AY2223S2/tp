package seedu.powercards.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_QUESTION = new Prefix("q\\");
    public static final Prefix PREFIX_ANSWER = new Prefix("a\\");
    public static final Prefix PREFIX_TAG = new Prefix("t\\");
    public static final Prefix REVIEW_EASY_FLAG = new Prefix("-e");
    public static final Prefix REVIEW_MEDIUM_FLAG = new Prefix("-m");
    public static final Prefix REVIEW_HARD_FLAG = new Prefix("-h");
    public static final Prefix REVIEW_UPPER_EASY_FLAG = new Prefix("-E");
    public static final Prefix REVIEW_UPPER_MEDIUM_FLAG = new Prefix("-M");
    public static final Prefix REVIEW_UPPER_HARD_FLAG = new Prefix("-H");
}
