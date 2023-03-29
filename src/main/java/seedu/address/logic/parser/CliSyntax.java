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
    public static final Prefix PREFIX_DEPARTMENT = new Prefix("d/");
    public static final Prefix PREFIX_PAYROLL = new Prefix("pr/");
    public static final Prefix PREFIX_LEAVE_COUNT = new Prefix("l/");
    public static final Prefix PREFIX_DATE_OF_BIRTH = new Prefix("dob/");
    public static final Prefix PREFIX_DATE_OF_JOINING = new Prefix("doj/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix[] PREFIX_LIST = {
        PREFIX_NAME,
        PREFIX_PHONE,
        PREFIX_EMAIL,
        PREFIX_ADDRESS,
        PREFIX_DEPARTMENT,
        PREFIX_PAYROLL,
        PREFIX_LEAVE_COUNT,
        PREFIX_DATE_OF_BIRTH,
        PREFIX_DATE_OF_JOINING,
        PREFIX_TAG
    };

}
