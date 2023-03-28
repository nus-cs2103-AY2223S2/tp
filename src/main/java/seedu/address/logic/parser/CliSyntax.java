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
    public static final Prefix PREFIX_EDUCATION = new Prefix("edu/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");
    private static final Prefix[] prefixList = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_ADDRESS, PREFIX_EDUCATION, PREFIX_REMARK, PREFIX_TAG, PREFIX_SUBJECT};

    /**
     * Checks if the specified prefix is a valid prefix implemented in the program.
     * @param prefix
     * @return
     */
    public static boolean isPrefix(Prefix prefix) {
        boolean isPrefix = false;
        for (Prefix curr : prefixList) {
            if (curr.equals(prefix)) {
                isPrefix = true;
                break;
            }
        }
        return isPrefix;
    }
}
