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
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("time/");

    public static final Prefix PREFIX_SENDER_ID = new Prefix("si/");
    public static final Prefix PREFIX_RECEPIENT_ID = new Prefix("ri/");
    public static final Prefix PREFIX_DELIVERY_DATE = new Prefix("date/");
    public static final Prefix PREFIX_DELIVERY_SLOT = new Prefix("slot/");
    public static final Prefix PREFIX_EARNING = new Prefix("earn/");
}
