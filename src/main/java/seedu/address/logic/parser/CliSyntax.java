package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_PARENT_PHONE = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TASK_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_SCORE_LABEL = new Prefix("l/");
    public static final Prefix PREFIX_SCORE_VALUE = new Prefix("v/");
    public static final Prefix PREFIX_SCORE_DATE = new Prefix("d/");

}
