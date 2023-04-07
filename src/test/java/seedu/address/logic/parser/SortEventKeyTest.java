package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortEventKeyTest {
    @Test
    public void getSortEventKey_invalidArg_returnsNull() {
        assertTrue(null == SortEventKey.getSortEventKey("1"));
    }

    @Test
    public void getSortEventKey_validArg_returnsSortEventKey() {
        assertTrue(SortEventKey.SORT_BY_NAME_ASC.equals(SortEventKey.getSortEventKey("a")));
        assertTrue(SortEventKey.SORT_BY_NAME_DESC.equals(SortEventKey.getSortEventKey("b")));
        assertTrue(SortEventKey.SORT_BY_START_DATE_TIME.equals(SortEventKey.getSortEventKey("c")));
        assertTrue(SortEventKey.SORT_BY_END_DATE_TIME.equals(SortEventKey.getSortEventKey("d")));
    }
}
