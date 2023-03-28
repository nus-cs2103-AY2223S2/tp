package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_QUESTION = new Prefix("q\\");
    public static final Prefix PREFIX_ANSWER = new Prefix("a\\");
    public static final Prefix PREFIX_TAG = new Prefix("t\\");
    public static final Prefix PREFIX_EASY_TAG = new Prefix("e\\");
    public static final Prefix PREFIX_MEDIUM_TAG = new Prefix("m\\");
    public static final Prefix PREFIX_HARD_TAG = new Prefix("h\\");
}
