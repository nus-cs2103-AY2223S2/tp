package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class NoDeadlineTest {
    @Test
    public void getDate_returnsNull() {
        NoDeadline noDeadline = new NoDeadline();

        assertNull(noDeadline.getDate());
    }

    @Test
    public void hashCodeTest_equals() {
        NoDeadline noDeadline = new NoDeadline();

        assertEquals(noDeadline.hashCode(), 0);
    }
    @Test
    public void equals() {
        NoDeadline noDeadline = new NoDeadline();
        NoDeadline noDeadLineCopy = new NoDeadline();

        // same object => returns true
        assertEquals(noDeadline, noDeadline);
        assertEquals(noDeadline, noDeadLineCopy);

        //no deadline should have the description "N/A"
        assertEquals(noDeadline.toString(), "N/A");
    }
}
