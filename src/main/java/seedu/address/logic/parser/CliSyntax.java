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
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");
    public static final Prefix PREFIX_SCHEDULE = new Prefix("sch/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("st/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("et/");
    public static final Prefix PREFIX_COPY = new Prefix("cp/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_LEARN = new Prefix("l/");
}
