package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "NAME");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "PHONE");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "EMAIL");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "ADDRESS");
    public static final Prefix PREFIX_EDUCATION = new Prefix("edu/", "EDUCATION");
    public static final Prefix PREFIX_REMARK = new Prefix("r/", "REMARK");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "TAG");
    public static final Prefix PREFIX_MODULE = new Prefix("m/", "MODULE");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("tele/", "TELEGRAM_HANDLE");

    /** Not a prefix, but a placeholder for a keyword argument. */
    public static final Prefix KEYWORD_PLACEHOLDER = new Prefix("", "KEYWORD");

    /** Not a prefix, but a placeholder for a index argument. */
    public static final Prefix INDEX_PLACEHOLDER = new Prefix("", "INDEX");

    /** Not a prefix, but a placeholder for a index argument. */
    public static final Prefix REMARK_PLACEHOLDER = new Prefix("", PREFIX_REMARK.getPlaceholderText());

    private static final Prefix[] prefixList = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_ADDRESS, PREFIX_EDUCATION, PREFIX_REMARK, PREFIX_TAG, PREFIX_MODULE};

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
