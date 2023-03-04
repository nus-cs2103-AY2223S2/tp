package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_TIME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CARNIVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_TIME_CARNIVAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(CARNIVAL));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(CARNIVAL);
        assertTrue(uniqueEventList.contains(CARNIVAL));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.add(CARNIVAL);
        Event editedCarnival = new EventBuilder(CARNIVAL).withName(VALID_EVENT_NAME_CARNIVAL)
                .withStartDateTime(VALID_START_DATE_TIME_CARNIVAL)
                .withEndDateTime(VALID_END_DATE_TIME_CARNIVAL)
                .build();
        assertTrue(uniqueEventList.contains(editedCarnival));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }
}
