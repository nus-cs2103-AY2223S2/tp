package seedu.task.model.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.TypicalTagComparator.CODE_3;
import static seedu.task.testutil.TypicalTagComparator.EAT_1;
import static seedu.task.testutil.TypicalTagComparator.SLEEP_5;

import org.junit.jupiter.api.Test;

public class TagComparatorTest {
    @Test
    public void getsName_tagComparator() {
        assertEquals("[CODE]", CODE_3.getName());
        assertEquals("[SLEEP]", SLEEP_5.getName());
        assertEquals("[EAT]", EAT_1.getName());
    }

    @Test
    public void getsOccurrences_tagComparator() {
        assertEquals(3, CODE_3.getOccurrences());
        assertEquals(5, SLEEP_5.getOccurrences());
        assertEquals(1, EAT_1.getOccurrences());
    }

    @Test
    public void compareTo_tagComparator() {
        assertEquals(2, CODE_3.compareTo(SLEEP_5));
        assertEquals(-2, SLEEP_5.compareTo(CODE_3));
        assertEquals(0, EAT_1.compareTo(EAT_1));
    }
}
