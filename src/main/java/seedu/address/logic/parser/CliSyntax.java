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
    public static final Prefix PREFIX_GRADELEVEL = new Prefix("lv/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_HOMEWORK = new Prefix("hw/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_EXAM = new Prefix("e/");

    public static final Prefix PREFIX_STATUS = new Prefix("st/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");

    public static final Prefix PREFIX_LESSON = new Prefix("l/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("start/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("end/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("sub/");
    public static final Prefix PREFIX_DONE = new Prefix("done/");
}
