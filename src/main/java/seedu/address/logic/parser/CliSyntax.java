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

    /* Policy Prefix definitions */
    public static final Prefix PREFIX_POLICY_NAME = new Prefix("pn/");
    public static final Prefix PREFIX_POLICY_START_DATE = new Prefix("pd/");
    public static final Prefix PREFIX_POLICY_PREMIUM = new Prefix("pp/");
    public static final Prefix PREFIX_POLICY_FREQUENCY = new Prefix("pf/");

}
