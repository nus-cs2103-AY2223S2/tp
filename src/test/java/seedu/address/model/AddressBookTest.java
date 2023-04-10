package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.ProgrammingLanguage;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.InternshipBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two applications with the same identity fields
        InternshipApplication editedAlice = new InternshipBuilder(ALICE)
                .withCompanyName("Alice Wonder")
                .build();
        List<InternshipApplication> newApplications = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newApplications);

        assertThrows(DuplicateApplicationException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasApplication(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addApplication(ALICE);
        assertTrue(addressBook.hasApplication(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addInternshipApplication(ALICE);
        InternshipApplication editedAlice = new InternshipBuilder(ALICE)
                .withProgrammingLanguage(new ProgrammingLanguage("Java"))
                .build();
        assertTrue(addressBook.hasApplication(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<InternshipApplication> applications = FXCollections.observableArrayList();

        AddressBookStub(Collection<InternshipApplication> persons) {
            this.applications.setAll(persons);
        }

        @Override
        public ObservableList<InternshipApplication> getInternshipList() {
            return applications;
        }
    }

}
