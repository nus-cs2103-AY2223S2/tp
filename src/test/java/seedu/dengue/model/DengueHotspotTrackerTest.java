package seedu.dengue.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV1_UPPERCASE;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.exceptions.DuplicatePersonException;
import seedu.dengue.testutil.PersonBuilder;

public class DengueHotspotTrackerTest {

    private final DengueHotspotTracker dengueHotspotTracker = new DengueHotspotTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), dengueHotspotTracker.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dengueHotspotTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDengueHotspotTracker_replacesData() {
        DengueHotspotTracker newData = getTypicalDengueHotspotTracker();
        dengueHotspotTracker.resetData(newData);
        assertEquals(newData, dengueHotspotTracker);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
                .withAge(VALID_AGE_ALICE)
                .withPostal(VALID_POSTAL_ALICE)
                .withVariants(VALID_VARIANT_DENV1_UPPERCASE)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        DengueHotspotTrackerStub newData = new DengueHotspotTrackerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> dengueHotspotTracker.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dengueHotspotTracker.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInDengueHotspotTracker_returnsFalse() {
        assertFalse(dengueHotspotTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInDengueHotspotTracker_returnsTrue() {
        dengueHotspotTracker.addPerson(ALICE);
        assertTrue(dengueHotspotTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInDengueHotspotTracker_returnsTrue() {
        dengueHotspotTracker.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAge(VALID_AGE_ALICE)
                .withPostal(VALID_POSTAL_ALICE).withVariants(VALID_VARIANT_DENV1_UPPERCASE)
                .build();
        assertTrue(dengueHotspotTracker.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> dengueHotspotTracker.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyDengueHotspotTracker whose persons list can violate interface constraints.
     */
    private static class DengueHotspotTrackerStub implements ReadOnlyDengueHotspotTracker {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        DengueHotspotTrackerStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        /**
         * Returns itself
         * @return itself.
         */
        @Override
        public ReadOnlyDengueHotspotTracker generateDeepCopy() {
            return this;
        }
    }

}
