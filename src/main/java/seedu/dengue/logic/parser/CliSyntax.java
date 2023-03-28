package seedu.dengue.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_POSTAL = new Prefix("p/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_STARTDATE = new Prefix("sd/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("ed/");
    public static final Prefix PREFIX_AGE = new Prefix("a/");
    public static final Prefix PREFIX_STARTAGE = new Prefix("sa/");
    public static final Prefix PREFIX_ENDAGE = new Prefix("ea/");
    public static final Prefix PREFIX_VARIANT = new Prefix("v/");

}
