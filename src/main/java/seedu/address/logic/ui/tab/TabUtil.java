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
    private static final TabType[] TAB_TYPES = TabType.getAll();

    private final List<TabInfo> tabInfoList;
    private final ReadOnlyObjectWrapper<TabInfo> selectedTabInfo;

    /**
     * Creates a {@code TabUtil}.
     */
    public TabUtil() {
        tabInfoList = new ArrayList<>(TAB_TYPES.length);
        for (int i = 0; i < TAB_TYPES.length; i++) {
            tabInfoList.add(new TabInfo(Index.fromZeroBased(i), TAB_TYPES[i]));
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
}
