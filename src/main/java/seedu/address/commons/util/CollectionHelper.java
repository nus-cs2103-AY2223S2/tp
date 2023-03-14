package seedu.address.commons.util;

import java.util.Collection;

/**
 * The singleton instance that wraps around the {@code CollectionUtil} class.
 */
public class CollectionHelper {
    /**
     * The singleton instance of this class.
     */
    public static final CollectionHelper INSTANCE;

    static {
        INSTANCE = new CollectionHelper();
    }

    private CollectionHelper() {
    }

    /**
     * @see CollectionUtil#requireAllNonNull(Object...)
     */
    public void requireAllNonNull(Object... items) {
        CollectionUtil.requireAllNonNull(items);
    }

    /**
     * @see CollectionUtil#requireAllNonNull(Collection)
     */
    public void requireAllNonNull(Collection<?> items) {
        CollectionUtil.requireAllNonNull(items);
    }

    /**
     * @see CollectionUtil#isAnyNonNull(Object...)
     */
    public boolean isAnyNonNull(Object... items) {
        return CollectionUtil.isAnyNonNull(items);
    }
}
