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
    public static final Prefix PREFIX_EVENT_SET = new Prefix("evt/");

    /* Prefix definitions for events */
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("ev/");
    public static final Prefix PREFIX_START_DATE_TIME = new Prefix("from/");
    public static final Prefix PREFIX_END_DATE_TIME = new Prefix("to/");
}
