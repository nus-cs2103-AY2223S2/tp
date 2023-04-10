package seedu.address.logic.ui.tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import seedu.address.commons.core.index.Index;

/**
 * Utility class to keep and manage {@code TabInfo}s.
 */
public class TabUtil {
    private final List<TabInfo> tabInfoList;
    private final ReadOnlyObjectWrapper<TabInfo> selectedTabInfo;

    /**
     * Creates a {@code TabUtil}.
     */
    public TabUtil(TabType ...tabTypes) {
        tabInfoList = new ArrayList<>(tabTypes.length);
        for (int i = 0; i < tabTypes.length; i++) {
            tabInfoList.add(new TabInfo(Index.fromZeroBased(i), tabTypes[i]));
        }
        selectedTabInfo = new ReadOnlyObjectWrapper<>(tabInfoList.get(0));
    }

    public List<TabInfo> getTabInfoList() {
        return tabInfoList;
    }

    /**
     * Returns whether the given {@code index} is in the range of valid tab indices.
     */
    public boolean isIndexInRange(Index index) {
        Objects.requireNonNull(index);
        int zeroBased = index.getZeroBased();
        return zeroBased > -1 && zeroBased < tabInfoList.size();
    }

    public ReadOnlyObjectProperty<TabInfo> getSelectedTab() {
        return selectedTabInfo.getReadOnlyProperty();
    }

    public void setSelectedTab(Index index) {
        if (isIndexInRange(index)) {
            selectedTabInfo.setValue(tabInfoList.get(index.getZeroBased()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TabUtil)) {
            return false;
        }

        TabUtil that = (TabUtil) other;
        return tabInfoList.equals(that.tabInfoList)
                && Objects.equals(selectedTabInfo.get(), that.selectedTabInfo.get());
    }
}
