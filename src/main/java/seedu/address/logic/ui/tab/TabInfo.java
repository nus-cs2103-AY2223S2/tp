package seedu.address.logic.ui.tab;

import seedu.address.commons.core.index.Index;

/**
 * Represents the information associated with a UI tab.
 */
public class TabInfo {
    private static final String STRING_REPRESENTATION = "%d: %s";
    private final Index index;
    private final String title;

    /**
     * Creates a {@code TabInfo} with the given {@code index} and {@code title}.
     */
    public TabInfo(Index index, String title) {
        this.index = index;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format(STRING_REPRESENTATION, index.getOneBased(), title);
    }
}
