package seedu.address.commons.util;

/**
 * The singleton instance that wraps around the {@code StringUtil} class.
 */
public class StringHelper {
    /**
     * The singleton instance of this class.
     */
    public static final StringHelper INSTANCE;

    static {
        INSTANCE = new StringHelper();
    }

    private StringHelper() {
    }

    /**
     * @see StringUtil#containsWordIgnoreCase(String, String)
     */
    public boolean containsWordIgnoreCase(String source, String word) {
        return StringUtil.containsWordIgnoreCase(source, word);
    }

    /**
     * @see StringUtil#getDetails(Throwable)
     */
    public String getDetails(Throwable throwable) {
        return StringUtil.getDetails(throwable);
    }

    /**
     * @see StringUtil#isNonZeroUnsignedInteger(String)
     */
    public boolean isNonZeroUnsignedInteger(String test) {
        return StringUtil.isNonZeroUnsignedInteger(test);
    }
}
