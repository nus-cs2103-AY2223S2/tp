package seedu.patientist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

//import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
//import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
//import seedu.patientist.model.person.exceptions.DuplicatePersonException;
import seedu.patientist.model.ward.Ward;
import seedu.patientist.testutil.PatientBuilder;

public class PatientistTest {

    private final Patientist patientist = new Patientist();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), patientist.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientist.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Patientist newData = getTypicalPatientist();
        patientist.resetData(newData);
        assertEquals(newData, patientist);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientist.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(patientist.hasPerson(AMY));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        Ward ward = new Ward("ward");
        patientist.addWard(ward);
        patientist.addPatient(AMY, ward);
        assertTrue(patientist.hasPerson(AMY));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        Ward ward = new Ward("ward");
        patientist.addWard(ward);
        patientist.addPatient(AMY, ward);
        Person editedAlice = new PatientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(patientist.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> patientist.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyPatientist whose persons list can violate interface constraints.
     */
    private static class PatientistStub implements ReadOnlyPatientist {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Ward> wards = FXCollections.observableArrayList();

        PatientistStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public void updatePersonList() {
            return;
        }

        @Override
        public ObservableList<Ward> getWardList() {
            return wards;
        }

        @Override
        public ObservableList<Person> getPatientListInWard(Ward ward) {
            return null;
        }

        @Override
        public ObservableList<Person> getStaffListInWard(Ward ward) {
            return null;
        }
    }

}
