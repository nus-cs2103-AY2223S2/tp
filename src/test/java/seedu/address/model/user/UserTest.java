package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.UserBuilder;

public class UserTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        User user = new UserBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> user.getSetOfTags().remove(0));
    }

    @Test
    public void isSameUser() {

        User user = new UserBuilder().withName("Neo").build();

        // same object -> returns true
        assertTrue(user.isSamePerson(user));

        // null -> returns false
        assertFalse(user.isSamePerson(null));

        // same name, all other attributes different -> returns true
        User editedUser = new UserBuilder().withName("Neo").withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(user.isSamePerson(editedUser));

        // different name, all other attributes same -> returns false
        User editedBob = new UserBuilder().withName(VALID_NAME_BOB).build();
        assertFalse(user.isSamePerson(editedBob));

        // name differs in case, all other attributes same -> returns false
        User editedUser2 = new UserBuilder().withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(editedUser.isSamePerson(editedUser2));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedUser = new UserBuilder().withName(nameWithTrailingSpaces).build();
        assertFalse(editedBob.isSamePerson(editedUser));
    }

    @Test
    public void equals() {
        // same values -> returns true
        User aliceCopy = new UserBuilder(ALICE).build();
        User aliceUser = new UserBuilder(ALICE).build();
        User bobUser = new UserBuilder(BOB).build();
        assertEquals(aliceUser, aliceCopy);

        // same object -> returns true
        assertEquals(aliceUser, aliceUser);

        // null -> returns false
        assertNotEquals(null, aliceUser);

        // different type -> returns false
        assertNotEquals(5, aliceUser);

        // different user -> returns false
        assertNotEquals(aliceUser, bobUser);

        // different name -> returns false
        User editedAlice = new UserBuilder().withName(VALID_NAME_BOB).build();
        assertNotEquals(aliceUser, editedAlice);

        // different phone -> returns false
        editedAlice = new UserBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(aliceUser, editedAlice);

        // different email -> returns false
        editedAlice = new UserBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(aliceUser, editedAlice);

        // different address -> returns false
        editedAlice = new UserBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(aliceUser, editedAlice);

        // different tags -> returns false
        editedAlice = new UserBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(aliceUser, editedAlice);
    }

    @Test
    public void hasEvent() {
        Event oneTimeEvent = SampleDataUtil.EVENT_1;
        Event recurringEvent = SampleDataUtil.EVENT_2;

        User user = new UserBuilder().build();

        // Fresh user has no events.
        assertFalse(user.hasEvent(oneTimeEvent));
        assertFalse(user.hasEvent(recurringEvent));

        //Add one time event, user contains one-time-event and not recurring event.
        user = new UserBuilder().withEvents(oneTimeEvent).build();
        assertTrue(user.hasEvent(oneTimeEvent));
        assertFalse(user.hasEvent(recurringEvent));

        //Add recurring event, user contains recurring event and not one-time-event.
        user = new UserBuilder().withEvents(recurringEvent).build();
        assertFalse(user.hasEvent(oneTimeEvent));
        assertTrue(user.hasEvent(recurringEvent));

        //Add both events, user contains both recurring event and one-time-event.
        user = new UserBuilder().withEvents(recurringEvent).withEvents(oneTimeEvent).build();
        assertTrue(user.hasEvent(oneTimeEvent));
        assertTrue(user.hasEvent(recurringEvent));
    }

    @Test
    public void addEvent() {
        Event oneTimeEvent = SampleDataUtil.EVENT_1;
        Event recurringEvent = SampleDataUtil.EVENT_2;

        User user = new UserBuilder().build();

        // Fresh user has no events.
        assertFalse(user.getEvents().contains(oneTimeEvent));
        assertFalse(user.getEvents().contains(recurringEvent));

        //Add one time event, user contains one-time-event and not recurring event.
        user = new UserBuilder().build();
        user.addEvent(oneTimeEvent);
        assertTrue(user.getEvents().contains(oneTimeEvent));
        assertFalse(user.getEvents().contains(recurringEvent));

        //Add recurring event, user contains recurring event and not one-time-event.
        user = new UserBuilder().build();
        user.addEvent(recurringEvent);
        assertFalse(user.getEvents().contains(oneTimeEvent));
        assertTrue(user.getEvents().contains(recurringEvent));

        //Add oneTimeEvent twice. Duplicate events are allowed.
        User finalUser = new UserBuilder().build();
        finalUser.addEvent(oneTimeEvent);
        finalUser.addEvent(oneTimeEvent);
        assertTrue(finalUser.hasEvent(oneTimeEvent));
        finalUser.deleteEvent(oneTimeEvent);
        //Should still have one more oneTimeEvent.
        assertTrue(finalUser.hasEvent(oneTimeEvent));
    }
}
