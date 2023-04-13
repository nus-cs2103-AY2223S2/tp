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
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("t/");
    public static final Prefix PREFIX_PERSON_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_TASK = new Prefix("task/");
    public static final Prefix PREFIX_DATE = new Prefix("by/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");
    public static final Prefix PREFIX_START_DATE = new Prefix("from/");
    public static final Prefix PREFIX_END_DATE = new Prefix("to/");
    public static final Prefix PREFIX_COMMENT = new Prefix("c/");
    public static final Prefix PREFIX_TASKTYPE = new Prefix("type/");
}
