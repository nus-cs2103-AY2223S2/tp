package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALEX;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.*;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.model.ward.Ward;
import seedu.address.testutil.PatientBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPatientList());
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
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALEX).withNric("S1334567A")
                .build();
        List<Patient> newPatients = Arrays.asList(ALEX, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPatient(ALEX));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPatient(ALEX);
        assertTrue(addressBook.hasPatient(ALEX));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPatient(ALEX);
        Patient editedAlice = new PatientBuilder(ALEX).withStatus("YELLOW")
                .build();
        assertTrue(addressBook.hasPatient(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose patients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Ward> wards = FXCollections.observableArrayList();

        AddressBookStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public void sortPatients(Comparator<Patient> comparator) {}

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Ward> getWardList() {
            return wards;
        }
    }

}
