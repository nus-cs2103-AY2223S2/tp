package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    public static final Prefix PREFIX_MODULE = new Prefix("/mod");
    public static final Prefix PREFIX_LECTURE = new Prefix("/lec");
    public static final Prefix PREFIX_VIDEO = new Prefix("/vid");

    public static final Prefix PREFIX_TAG = new Prefix("/tags");
}
