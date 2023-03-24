package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    /* Prefix used in AddCommand */
    public static final Prefix PREFIX_COMPANY_NAME = new Prefix("n/");
    public static final Prefix PREFIX_NAME = new Prefix("deprecated/");
    public static final Prefix PREFIX_JOB_TITLE = new Prefix("j/");
    public static final Prefix PREFIX_REVIEW = new Prefix("r/");
    /* Prefix used in AddContactCommand */
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("b/");
    public static final Prefix PREFIX_NOTE_CONTENT = new Prefix("c/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");

    /* Prefix used in FindDateCommand */
    public static final Prefix PREFIX_DATE_BEFORE = new Prefix("before/");
    public static final Prefix PREFIX_DATE_AFTER = new Prefix("after/");
    public static final Prefix PREFIX_DATE_FROM = new Prefix("from/");
    public static final Prefix PREFIX_DATE_TO = new Prefix("to/");
}
