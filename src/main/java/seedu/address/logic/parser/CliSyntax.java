package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix string definitions */
    public static final String PREFIX_NAME_STRING = "n/";
    public static final String PREFIX_PHONE_STRING = "p/";
    public static final String PREFIX_EMAIL_STRING = "e/";
    public static final String PREFIX_ADDRESS_STRING = "a/";
    public static final String PREFIX_TELEGRAM_HANDLE_STRING = "t/";
    public static final String PREFIX_MODULE_TAG_STRING = "m/";
    public static final String PREFIX_GROUP_TAG_STRING = "g/";
    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix(PREFIX_NAME_STRING);
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TELEGRAM_HANDLE = new Prefix("t/");
    public static final Prefix PREFIX_MODULE_TAG = new Prefix("m/");
    public static final Prefix PREFIX_GROUP_TAG = new Prefix("g/");

}
