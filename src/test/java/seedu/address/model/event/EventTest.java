package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.enums.Interval;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

class EventTest {
    private static final Description DESCRIPTION = new Description("Hello world!");
    private static final DateTime START_DATE_TIME = new DateTime("2023-01-01 0000");
    private static final DateTime END_DATE_TIME = new DateTime("2023-01-01 1200");
    private static final Recurrence RECURRENCE = new Recurrence(Interval.DAILY);
    private static final Set<Person> PEOPLE = Set.of(ALICE, CARL);
    private static final Event ONE_TIME_EVENT = new OneTimeEvent(DESCRIPTION,
            START_DATE_TIME, END_DATE_TIME, PEOPLE);
    private static final Event RECURRING_EVENT = new RecurringEvent(DESCRIPTION,
            START_DATE_TIME, END_DATE_TIME, RECURRENCE, PEOPLE);

    @Test
    void copyTest() {
        assertNotSame(ONE_TIME_EVENT, ONE_TIME_EVENT.copy());
        assertNotSame(RECURRING_EVENT, RECURRING_EVENT.copy());
        assertEquals(ONE_TIME_EVENT, ONE_TIME_EVENT.copy());
        assertEquals(RECURRING_EVENT, RECURRING_EVENT.copy());

        Event duplicate = new RecurringEvent(
                new Description(RECURRING_EVENT.getDescription().getDescription()),
                new DateTime(RECURRING_EVENT.getStartDateTime().toString()),
                new DateTime(RECURRING_EVENT.getEndDateTime().toString()),
                new Recurrence(RECURRING_EVENT.getRecurrence().interval),
                new HashSet<>(RECURRING_EVENT.getTaggedPeople())
        );
        RECURRING_EVENT.updateDateTime();
        assertEquals(duplicate, RECURRING_EVENT);
    }

    @Test
    void getDescription() {
        assertEquals(DESCRIPTION, ONE_TIME_EVENT.getDescription());
        assertEquals(DESCRIPTION, RECURRING_EVENT.getDescription());
    }

    @Test
    void getStartDateTime() {
        assertEquals(START_DATE_TIME, ONE_TIME_EVENT.getStartDateTime());
        assertEquals(START_DATE_TIME, RECURRING_EVENT.getStartDateTime());
    }

    @Test
    void getEndDateTime() {
        assertEquals(END_DATE_TIME, ONE_TIME_EVENT.getEndDateTime());
        assertEquals(END_DATE_TIME, RECURRING_EVENT.getEndDateTime());
    }

    @Test
    void getRecurrence() {
        assertEquals(new Recurrence(Interval.NONE), ONE_TIME_EVENT.getRecurrence());
        assertEquals(RECURRENCE, RECURRING_EVENT.getRecurrence());
    }

    @Test
    void getTaggedPeople() {
        assertEquals(PEOPLE, ONE_TIME_EVENT.getTaggedPeople());
        assertEquals(PEOPLE, RECURRING_EVENT.getTaggedPeople());
    }

    @Test
    void isRecurring() {
        assertTrue(RECURRING_EVENT.isRecurring());
        assertFalse(ONE_TIME_EVENT.isRecurring());
    }

    @Test
    void updateDateTime_oneTimeEvent() {
        Event oldCopy = ONE_TIME_EVENT.copy();
        Event newCopy = ONE_TIME_EVENT.updateDateTime();
        assertEquals(oldCopy, ONE_TIME_EVENT);
        assertEquals(oldCopy, newCopy);
    }

    @Test
    void updateDateTime_recurringEvent_noneRecurrence() {
        Event event = new RecurringEvent(DESCRIPTION, START_DATE_TIME, END_DATE_TIME,
                new Recurrence(Interval.NONE), PEOPLE);
        Event oldCopy = event.copy();
        Event newCopy = event.updateDateTime();
        assertEquals(oldCopy, event);
        assertEquals(oldCopy, newCopy);
    }

    @Test
    void updateDateTime_recurringEvent_recurring() {
        Event oldCopy = RECURRING_EVENT.copy();
        Event newCopy = RECURRING_EVENT.updateDateTime();
        assertEquals(oldCopy, RECURRING_EVENT);
        assertNotEquals(oldCopy, newCopy);
        assertTrue(newCopy.getEndDateTime().getDateTime().isAfter(LocalDateTime.now()));
    }

    @Test
    void addTaggedPerson_differentPerson() {
        Event oldCopy = ONE_TIME_EVENT.copy();
        Event newCopy = ONE_TIME_EVENT.addTaggedPerson(BOB);
        assertEquals(oldCopy, ONE_TIME_EVENT);
        assertNotEquals(oldCopy, newCopy);

        Set<Person> expectedPeople = new HashSet<>(PEOPLE);
        expectedPeople.add(BOB);
        assertEquals(expectedPeople, newCopy.getTaggedPeople());
    }

    @Test
    void addTaggedPerson_samePerson() {
        Event newCopy = ONE_TIME_EVENT.addTaggedPerson(ALICE);
        assertEquals(ONE_TIME_EVENT, newCopy);
        assertEquals(ONE_TIME_EVENT.getTaggedPeople(), newCopy.getTaggedPeople());
    }

    @Test
    void editTaggedPerson_differentPerson() {
        Event oldCopy = ONE_TIME_EVENT.copy();
        Event newCopy = ONE_TIME_EVENT.editTaggedPerson(ALICE, BOB);
        assertEquals(oldCopy, ONE_TIME_EVENT);
        assertNotEquals(oldCopy, newCopy);

        Set<Person> expectedPeople = new HashSet<>(PEOPLE);
        expectedPeople.remove(ALICE);
        expectedPeople.add(BOB);
        assertEquals(expectedPeople, newCopy.getTaggedPeople());
    }

    @Test
    void editTaggedPerson_samePerson() {
        Event newCopy = ONE_TIME_EVENT.editTaggedPerson(ALICE, ALICE);
        assertEquals(ONE_TIME_EVENT, newCopy);
        assertEquals(ONE_TIME_EVENT.getTaggedPeople(), newCopy.getTaggedPeople());
    }

    @Test
    void deleteTaggedPerson_personExists() {
        Event oldCopy = ONE_TIME_EVENT.copy();
        Event newCopy = ONE_TIME_EVENT.deleteTaggedPerson(ALICE);
        assertEquals(oldCopy, ONE_TIME_EVENT);
        assertNotEquals(oldCopy, newCopy);

        Set<Person> expectedPeople = new HashSet<>(PEOPLE);
        expectedPeople.remove(ALICE);
        assertEquals(expectedPeople, newCopy.getTaggedPeople());
    }

    @Test
    void deleteTaggedPerson_personMissing() {
        Event newCopy = ONE_TIME_EVENT.deleteTaggedPerson(BOB);
        assertEquals(ONE_TIME_EVENT, newCopy);
        assertEquals(ONE_TIME_EVENT.getTaggedPeople(), newCopy.getTaggedPeople());
    }

    @Test
    void hasTaggedPerson() {
        assertTrue(ONE_TIME_EVENT.hasTaggedPerson(ALICE));
        assertTrue(ONE_TIME_EVENT.hasTaggedPerson(CARL));
        assertFalse(ONE_TIME_EVENT.hasTaggedPerson(BOB));
    }

    @Test
    void getEffectiveStartDateTime_oneTimeEvent() {
        assertEquals(ONE_TIME_EVENT.getStartDateTime(), ONE_TIME_EVENT.getEffectiveStartDateTime());
    }

    @Test
    void getEffectiveStartDateTime_recurringEvent() {
        assertTrue(RECURRING_EVENT.getStartDateTime().getDateTime()
                .isBefore(RECURRING_EVENT.getEffectiveStartDateTime().getDateTime()));
    }

    @Test
    void getEffectiveEndDateTime_oneTimeEvent() {
        assertEquals(ONE_TIME_EVENT.getEndDateTime(), ONE_TIME_EVENT.getEffectiveEndDateTime());
    }

    @Test
    void getEffectiveEndDateTime_recurringEvent() {
        assertTrue(RECURRING_EVENT.getEndDateTime().getDateTime()
                .isBefore(RECURRING_EVENT.getEffectiveEndDateTime().getDateTime()));
    }

    @Test
    void isSameEvent_differentDescription() {
        Event other = new RecurringEvent(new Description("Goodbye world!"),
                START_DATE_TIME, END_DATE_TIME, RECURRENCE, PEOPLE);
        assertFalse(ONE_TIME_EVENT.isSameEvent(other));
    }

    @Test
    void isSameEvent_differentEverythingElse() {
        Event other = new OneTimeEvent(DESCRIPTION,
                new DateTime("2022-02-02 0202"),
                new DateTime("2022-02-02 0203"),
                Set.of(BOB));
        assertTrue(RECURRING_EVENT.isSameEvent(other));
    }

    @Test
    void equalsTest() {
        assertNotEquals(null, ONE_TIME_EVENT);
        assertNotEquals(null, RECURRING_EVENT);
        Event otherOneTime = new OneTimeEvent(DESCRIPTION, START_DATE_TIME, END_DATE_TIME, PEOPLE);
        Event otherRecurring = new RecurringEvent(DESCRIPTION, START_DATE_TIME, END_DATE_TIME, RECURRENCE, PEOPLE);
        assertEquals(ONE_TIME_EVENT, otherOneTime);
        assertEquals(RECURRING_EVENT, otherRecurring);
    }
}
