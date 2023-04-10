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
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    //public static final Prefix PREFIX_FAVORITE = new Prefix("f/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_MAJOR = new Prefix("m/");
    public static final Prefix PREFIX_MODULES = new Prefix("mt/");
    public static final Prefix PREFIX_RACE = new Prefix("r/");
    public static final Prefix PREFIX_COMMS = new Prefix("c/");

    public static final Prefix PREFIX_FACULTY = new Prefix("f/");

    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_START_DATE_TIME = new Prefix("s/");
    public static final Prefix PREFIX_END_DATE_TIME = new Prefix("e/");
    public static final Prefix PREFIX_RECURRENCE = new Prefix("r/");

    public static final Prefix PREFIX_PERSON_TO_TAG = new Prefix("pt/");

    public static final Prefix PREFIX_EVENT_TO_TAG = new Prefix("et/");

}
