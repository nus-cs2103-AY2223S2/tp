package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalElister;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ElisterTest {

    private final Elister elister = new Elister();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), elister.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> elister.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyElister_replacesData() {
        Elister newData = getTypicalElister();
        elister.resetData(newData);
        assertEquals(newData, elister);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ElisterStub newData = new ElisterStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> elister.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> elister.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInElister_returnsFalse() {
        assertFalse(elister.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInElister_returnsTrue() {
        elister.addPerson(ALICE);
        assertTrue(elister.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInElister_returnsTrue() {
        elister.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(elister.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> elister.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyElister whose persons list can violate interface constraints.
     */
    private static class ElisterStub implements ReadOnlyElister {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ElisterStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
