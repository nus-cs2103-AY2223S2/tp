package ezschedule.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ezschedule.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.CommandTestUtil;
import ezschedule.model.event.Event;
import ezschedule.model.event.exceptions.DuplicateEventException;
import ezschedule.testutil.Assert;
import ezschedule.testutil.PersonBuilder;
import ezschedule.testutil.TypicalPersons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchedulerTest {

    private final Scheduler scheduler = new Scheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduler.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> scheduler.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Scheduler newData = TypicalPersons.getTypicalAddressBook();
        scheduler.resetData(newData);
        assertEquals(newData, scheduler);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        SchedulerStub newData = new SchedulerStub(newPersons);

        Assert.assertThrows(DuplicateEventException.class, () -> scheduler.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> scheduler.hasEvent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(scheduler.hasEvent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        scheduler.addEvent(TypicalPersons.ALICE);
        assertTrue(scheduler.hasEvent(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        scheduler.addEvent(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(scheduler.hasEvent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> scheduler.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class SchedulerStub implements ReadOnlyScheduler {
        private final ObservableList<Event> persons = FXCollections.observableArrayList();

        SchedulerStub(Collection<Event> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return persons;
        }
    }

}
