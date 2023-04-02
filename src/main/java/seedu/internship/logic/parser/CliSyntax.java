package seedu.internship.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_POSITION = new Prefix("p/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    //Event prefixes , can be changed later on
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("na/");
    public static final Prefix PREFIX_EVENT_START = new Prefix("st/");
    public static final Prefix PREFIX_EVENT_END = new Prefix("en/");
    public static final Prefix PREFIX_EVENT_DESCRIPTION = new Prefix("de/");
}

