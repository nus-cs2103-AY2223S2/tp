package seedu.address.logic.parser;

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
}
