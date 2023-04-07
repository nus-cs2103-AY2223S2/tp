package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_CARNIVAL;
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
        assertTrue(CARNIVAL.isSameEvent(carnivalCopy));

        // same object -> returns true
        assertTrue(CARNIVAL.equals(CARNIVAL));
        assertTrue(CARNIVAL.hashCode() == CARNIVAL.hashCode());
        assertTrue(CARNIVAL.isSameEvent(CARNIVAL));

        // null -> returns false
        assertFalse(CARNIVAL.equals(null));
        assertFalse(CARNIVAL.isSameEvent(null));

        // different type -> returns false
        assertFalse(CARNIVAL.equals(511));

        // different event -> returns false
        assertFalse(CARNIVAL.equals(SPORTS_DAY));
        assertFalse(CARNIVAL.hashCode() == SPORTS_DAY.hashCode());
        assertFalse(CARNIVAL.isSameEvent(SPORTS_DAY));

        Event editedCarnival = new EventBuilder(CARNIVAL).withName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL)
                .build();
        // different event with same name -> returns false
        assertTrue(CARNIVAL.equals(editedCarnival));
        assertTrue(CARNIVAL.isSameEvent(editedCarnival));
    }
}
