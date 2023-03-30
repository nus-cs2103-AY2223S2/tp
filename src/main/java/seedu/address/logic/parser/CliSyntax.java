package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/name");
    public static final Prefix PREFIX_CODE = new Prefix("/code");
    public static final Prefix PREFIX_TAG = new Prefix("/tags");
    public static final Prefix PREFIX_WATCH = new Prefix("/watch");
    public static final Prefix PREFIX_UNWATCH = new Prefix("/unwatch");
    public static final Prefix PREFIX_TIMESTAMP = new Prefix("/timestamp");
    public static final Prefix PREFIX_BY_TAG = new Prefix("/byTag");

    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");


    public static final Prefix PREFIX_ROOT = new Prefix("/r");
    public static final Prefix PREFIX_MODULE = new Prefix("/mod");
    public static final Prefix PREFIX_LECTURE = new Prefix("/lec");
    public static final Prefix PREFIX_VIDEO = new Prefix("/vid");
    public static final Prefix PREFIX_OVERWRITE = new Prefix("/overwrite");
    
}
