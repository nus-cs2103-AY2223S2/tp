package trackr.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_ORDERNAME = new Prefix("on/");
    public static final Prefix PREFIX_ORDERQUANTITY = new Prefix("q/");

    public static final Prefix PREFIX_PRICE = new Prefix("pr/");
    public static final Prefix PREFIX_COST = new Prefix("c/");

    public static final Prefix PREFIX_TAB = new Prefix("t/");

    public static final Prefix PREFIX_CRITERIA = new Prefix("c/");
}
