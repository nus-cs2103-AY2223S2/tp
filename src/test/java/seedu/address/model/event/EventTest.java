package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalEvents.SPORTS_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void equals() {
        // same values -> returns true
        Event carnivalCopy = new EventBuilder(CARNIVAL).build();
        assertTrue(CARNIVAL.equals(carnivalCopy));
        assertTrue(CARNIVAL.hashCode() == carnivalCopy.hashCode());

        // same object -> returns true
        assertTrue(CARNIVAL.equals(CARNIVAL));
        assertTrue(CARNIVAL.hashCode() == CARNIVAL.hashCode());

        // null -> returns false
        assertFalse(CARNIVAL.equals(null));

        // different type -> returns false
        assertFalse(CARNIVAL.equals(511));

        // different event -> returns false
        assertFalse(CARNIVAL.equals(SPORTS_DAY));
        assertFalse(CARNIVAL.hashCode() == SPORTS_DAY.hashCode());
    }
}
