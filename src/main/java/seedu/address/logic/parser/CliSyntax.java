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
    public static final Prefix PREFIX_MODULE_TAG = new Prefix("mt/");
    public static final Prefix PREFIX_COMMITMENT_TAG = new Prefix("ct/");
    public static final Prefix PREFIX_ADD_IMAGE = new Prefix("ai/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");

}
