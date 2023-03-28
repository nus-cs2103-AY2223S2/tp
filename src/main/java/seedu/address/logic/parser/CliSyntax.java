package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "NAME");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "PHONE");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "EMAIL");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "ADDRESS");
    public static final Prefix PREFIX_EDUCATION = new Prefix("edu/", "EDUCATION");
    public static final Prefix PREFIX_REMARK = new Prefix("r/", "REMARK");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "TAG");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/", "SUBJECT");

    /** Not a prefix, but a placeholder for a keyword argument. */
    public static final Prefix PREFIX_KEYWORD = new Prefix("", "KEYWORD");

    /** Not a prefix, but a placeholder for a index argument. */
    public static final Prefix PREFIX_INDEX = new Prefix("", "INDEX");

}
