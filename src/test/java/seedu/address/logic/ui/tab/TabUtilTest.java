package seedu.address.logic.ui.tab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

/**
 * Contains unit tests for {@code TabUtil}.
 */
class TabUtilTest {

    private final TabUtil tabUtil = new TabUtil(TabType.ADDRESS_BOOK, TabType.EVENTS);
    private final TabInfo addressBookTabInfo = new TabInfo(Index.fromZeroBased(0), TabType.ADDRESS_BOOK);
    private final TabInfo calendarTabInfo = new TabInfo(Index.fromZeroBased(1), TabType.EVENTS);
    private final List<TabInfo> tabInfoList = List.of(addressBookTabInfo, calendarTabInfo);

    @Test
    void getTabInfoList_success() {
        List<TabInfo> testList = tabUtil.getTabInfoList();
        assertEquals(tabInfoList.size(), testList.size());
        for (int i = 0; i < tabInfoList.size(); i++) {
            assertEquals(tabInfoList.get(i), testList.get(i));
        }
    }

    @Test
    void isIndexInRange_validInput_returnsFalse() {
        for (int i = 0; i < 2; i++) {
            assertTrue(tabUtil.isIndexInRange(Index.fromZeroBased(i)));
        }
    }

    @Test
    void isIndexInRange_invalidInput_returnsFalse() {
        Index indexExceeded = Index.fromZeroBased(2);
        assertFalse(tabUtil.isIndexInRange(indexExceeded));
    }

    @Test
    void setSelectedTab_validSelection_returnsNewTabInfo() {
        tabUtil.setSelectedTab(addressBookTabInfo.getIndex());
        assertEquals(addressBookTabInfo, tabUtil.getSelectedTab().get());
        tabUtil.setSelectedTab(calendarTabInfo.getIndex());
        assertEquals(calendarTabInfo, tabUtil.getSelectedTab().get());
    }

    @Test
    void setSelectedTab_invalidSelection_returnsOldTabInfo() {
        TabInfo oldTabInfo = tabUtil.getSelectedTab().get();
        tabUtil.setSelectedTab(Index.fromZeroBased(2));
        TabInfo newTabInfo = tabUtil.getSelectedTab().get();
        assertEquals(oldTabInfo, newTabInfo);
    }
}
