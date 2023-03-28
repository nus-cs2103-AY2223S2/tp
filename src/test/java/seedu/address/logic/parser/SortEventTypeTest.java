package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortEventTypeTest {
    @Test
    public void getSortEventType_invalidArg_returnsNull() {
        assertTrue(null == SortEventType.getSortEventType("1"));
    }

    @Test
    public void getSortEventType_validArg_returnsSortEventType() {
        assertTrue(SortEventType.SORT_BY_NAME_ASC.equals(SortEventType.getSortEventType("a")));
        assertTrue(SortEventType.SORT_BY_NAME_DESC.equals(SortEventType.getSortEventType("b")));
        assertTrue(SortEventType.SORT_BY_START_DATE_TIME.equals(SortEventType.getSortEventType("c")));
        assertTrue(SortEventType.SORT_BY_END_DATE_TIME.equals(SortEventType.getSortEventType("d")));
    }
}
