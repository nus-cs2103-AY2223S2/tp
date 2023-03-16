package seedu.address.logic.ui.tab;

import seedu.address.commons.core.index.Index;

/**
 * Represents the information associated with a UI tab.
 */
public class TabInfo {
    private static final String STRING_REPRESENTATION = "%d: %s";
    private final Index index;
    private final TabType tabType;

    /**
     * Creates a {@code TabInfo} with the given {@code index} and {@code title}.
     */
    public TabInfo(Index index, TabType tabType) {
        this.index = index;
        this.tabType = tabType;
    }

    public Index getIndex() {
        return index;
    }

    public TabType getTabType() {
        return tabType;
    }

    @Override
    public String toString() {
        return String.format(STRING_REPRESENTATION, index.getOneBased(), tabType.getTitle());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TabInfo)) {
            return false;
        }
        TabInfo that = (TabInfo) other;
        return index.equals(that.index)
                && tabType.equals(that.tabType);
    }
}
