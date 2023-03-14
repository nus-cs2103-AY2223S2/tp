package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class TuteeManagingSystemTest {

    private final TuteeManagingSystem tuteeManagingSystem = new TuteeManagingSystem();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tuteeManagingSystem.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuteeManagingSystem.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TuteeManagingSystem newData = getTypicalAddressBook();
        tuteeManagingSystem.resetData(newData);
        assertEquals(newData, tuteeManagingSystem);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two tutees with the same identity fields
        Tutee editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Tutee> newTutees = Arrays.asList(ALICE, editedAlice);
        TuteeManagingSystemStub newData = new TuteeManagingSystemStub(newTutees);

        assertThrows(DuplicatePersonException.class, () -> tuteeManagingSystem.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuteeManagingSystem.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(tuteeManagingSystem.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        tuteeManagingSystem.addPerson(ALICE);
        assertTrue(tuteeManagingSystem.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        tuteeManagingSystem.addPerson(ALICE);
        Tutee editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(tuteeManagingSystem.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tuteeManagingSystem.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTuteeManagingSystem whose tutees list can violate interface constraints.
     */
    private static class TuteeManagingSystemStub implements ReadOnlyTuteeManagingSystem {
        private final ObservableList<Tutee> tutees = FXCollections.observableArrayList();

        TuteeManagingSystemStub(Collection<Tutee> tutees) {
            this.tutees.setAll(tutees);
        }

        @Override
        public ObservableList<Tutee> getPersonList() {
            return tutees;
        }
    }

}
