package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortEventKey;
import seedu.address.model.event.exceptions.SortComparatorException;

public class EventComparatorTest {
    @Test
    public void getComparator_invalidArg_throwsSortComparatorException() {
        try {
            assertFalse(null == EventComparator.getComparator(SortEventKey.SORT_BY_START_DATE_TIME));
        } catch (SortComparatorException e) {
            assertEquals("Invalid sort type, unable to return sort comparator",
                    e.getMessage());
        }
    }
}
