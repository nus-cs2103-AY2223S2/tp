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
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.enums.Interval;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;
import seedu.address.testutil.UserBuilder;

public class UserTest {

    private static final Description DESCRIPTION = new Description("Hello world!");
    private static final DateTime START_DATE_TIME = new DateTime("2023-01-01 0000");
    private static final DateTime END_DATE_TIME = new DateTime("2023-01-01 1200");
    private static final Recurrence RECURRENCE = new Recurrence(Interval.DAILY);
    private static final Set<Person> PEOPLE = Set.of(DANIEL, ELLE);
    private static final Event ONE_TIME_EVENT = new OneTimeEvent(DESCRIPTION,
            START_DATE_TIME, END_DATE_TIME, PEOPLE);
    private static final Event RECURRING_EVENT = new RecurringEvent(DESCRIPTION,
            START_DATE_TIME, END_DATE_TIME, RECURRENCE, PEOPLE);

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
    public void hasEvent_noEvents() {

        User user = new UserBuilder().build();

        // Fresh user has no events.
        assertFalse(user.hasEvent(ONE_TIME_EVENT));
        assertFalse(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void hasEvent_oneTimeEvent() {

        //Add one time event, user contains one-time-event and not recurring event.
        User user = new UserBuilder().withEvents(ONE_TIME_EVENT).build();
        assertTrue(user.hasEvent(ONE_TIME_EVENT));
        assertFalse(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void hasEvent_recurringEvent() {
        //Add recurring event, user contains recurring event and not one-time-event.
        User user = new UserBuilder().withEvents(RECURRING_EVENT).build();
        assertFalse(user.hasEvent(ONE_TIME_EVENT));
        assertTrue(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void hasEvent_multipleEvents() {
        //Add both events, user contains both recurring event and one-time-event.
        User user = new UserBuilder().withEvents(RECURRING_EVENT).withEvents(ONE_TIME_EVENT).build();
        assertTrue(user.hasEvent(ONE_TIME_EVENT));
        assertTrue(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void addEvent_oneTimeEvent() {

        //Add one time event, user contains one-time-event and not recurring event.
        User user = new UserBuilder().build();
        user.addEvent(ONE_TIME_EVENT);
        assertTrue(user.hasEvent(ONE_TIME_EVENT));
        assertFalse(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void addEvent_recurringEvent() {
        //Add recurring event, user contains recurring event and not one-time-event.
        User user = new UserBuilder().build();
        user.addEvent(RECURRING_EVENT);
        assertFalse(user.hasEvent(ONE_TIME_EVENT));
        assertTrue(user.hasEvent(RECURRING_EVENT));
    }
    @Test
    public void addEvent_multipleEvents() {
        //Add recurring event and one time event, user contains both recurring event and one-time-event.
        User user = new UserBuilder().build();
        user.addEvent(RECURRING_EVENT);
        user.addEvent(ONE_TIME_EVENT);
        assertTrue(user.hasEvent(ONE_TIME_EVENT));
        assertTrue(user.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void addEvent_duplicateEvent() {
        //Add ONE_TIME_EVENT twice. Duplicate events are allowed.
        User finalUser = new UserBuilder().build();
        finalUser.addEvent(ONE_TIME_EVENT);
        finalUser.addEvent(ONE_TIME_EVENT);
        int count = 0;
        for (Event e: finalUser.getEvents()) {
            if (e.equals(ONE_TIME_EVENT)) {
                count += 1;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void deleteEvent_throwsEventNotFoundException() {
        User user = new UserBuilder().build();

        // Fresh user has no events. Throws EventNotFoundException.
        assertFalse(user.getEvents().contains(ONE_TIME_EVENT));
        assertThrows(EventNotFoundException.class, () -> user.deleteEvent(ONE_TIME_EVENT));

        // User with one time event, does not have recurring event.
        User userWithOneTimeEvent = new UserBuilder().withEvents(ONE_TIME_EVENT).build();
        assertThrows(EventNotFoundException.class, () -> userWithOneTimeEvent.deleteEvent(RECURRING_EVENT));

        // User with one time event, does not have recurring event.
        User userWithRecurringEvent = new UserBuilder().withEvents(RECURRING_EVENT).build();
        assertThrows(EventNotFoundException.class, () -> userWithRecurringEvent.deleteEvent(ONE_TIME_EVENT));
    }

    @Test
    public void deleteEvent_oneTimeEvent() {
        // User with one time event can delete one time event.
        User userWithOneTimeEvent = new UserBuilder().withEvents(ONE_TIME_EVENT).build();
        assertTrue(userWithOneTimeEvent.hasEvent(ONE_TIME_EVENT));
        userWithOneTimeEvent.deleteEvent(ONE_TIME_EVENT);
        assertFalse(userWithOneTimeEvent.hasEvent(ONE_TIME_EVENT));
    }

    @Test
    public void deleteEvent_recurringEvent() {
        // User with recurring event can delete recurring event but not one time event.
        User userWithRecurringEvent = new UserBuilder().withEvents(RECURRING_EVENT).build();
        assertTrue(userWithRecurringEvent.hasEvent(RECURRING_EVENT));
        userWithRecurringEvent.deleteEvent(RECURRING_EVENT);
        assertFalse(userWithRecurringEvent.hasEvent(RECURRING_EVENT));
    }

    @Test
    public void deleteEvent_multipleEvents() {
        // User with both recurring event and one time event can delete both events.
        User userWithBothEvents = new UserBuilder().withEvents(RECURRING_EVENT).withEvents(ONE_TIME_EVENT).build();
        assertTrue(userWithBothEvents.hasEvent(RECURRING_EVENT));
        assertTrue(userWithBothEvents.hasEvent(ONE_TIME_EVENT));
        userWithBothEvents.deleteEvent(RECURRING_EVENT);
        userWithBothEvents.deleteEvent(ONE_TIME_EVENT);
        assertFalse(userWithBothEvents.hasEvent(RECURRING_EVENT));
        assertFalse(userWithBothEvents.hasEvent(ONE_TIME_EVENT));
    }
}
