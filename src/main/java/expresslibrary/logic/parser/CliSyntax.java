package expresslibrary.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BOOK = new Prefix("b/");
    public static final Prefix PREFIX_DUEDATE = new Prefix("d/");
    public static final Prefix PREFIX_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_AUTHOR = new Prefix("a/");
    public static final Prefix PREFIX_ISBN = new Prefix("i/");
    public static final Prefix PREFIX_DUE_DATE = new Prefix("dd/");
    public static final Prefix PREFIX_BORROW_DATE = new Prefix("bd/");
    public static final Prefix PREFIX_FORCE = new Prefix("-f");
}
