package trackr.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TabEnumTest {

    @Test
    public void getTabIndex_validIndex_success() {
        assertEquals(TabEnum.getTabIndex("HOME"), 0);

        assertEquals(TabEnum.getTabIndex("ORDERS"), 1);

        assertEquals(TabEnum.getTabIndex("TASKS"), 2);

        assertEquals(TabEnum.getTabIndex("CONTACTS"), 3);

        assertEquals(TabEnum.getTabIndex("MENU"), 4);

        assertEquals(TabEnum.getTabIndex("OTHERS"), 5);
    }

    @Test
    public void getTabIndex_validIndex_failure() {
        assertThrows(NullPointerException.class, () -> TabEnum.getTabIndex(null));

        assertThrows(IllegalArgumentException.class, () -> TabEnum.getTabIndex("Not valid"));
    }

}
