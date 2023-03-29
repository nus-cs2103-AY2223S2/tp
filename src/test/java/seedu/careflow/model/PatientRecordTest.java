package seedu.careflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.exceptions.DuplicatePatientException;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;
import seedu.careflow.testutil.PatientBuilder;

public class PatientRecordTest {

    private final PatientRecord patientRecord = new PatientRecord();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), patientRecord.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientRecord.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPatientRecord_replacesData() {
        PatientRecord newData = getTypicalPatientRecord();
        patientRecord.resetData(newData);
        assertEquals(newData, patientRecord);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        PatientRecordStub newData = new PatientRecordStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> patientRecord.resetData(newData));
    }

    @Test
    public void hasSamePatientName_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientRecord.hasSamePatientName(null));
    }

    @Test
    public void hasSamePatientName_patientNotInPatientRecord_returnsFalse() {
        assertFalse(patientRecord.hasSamePatientName(ALICE));
    }

    @Test
    public void hasSamePatientName_patientInPatientRecord_returnsTrue() {
        patientRecord.addPatient(ALICE);
        assertTrue(patientRecord.hasSamePatientName(ALICE));
    }

    @Test
    public void hasSamePatientName_patientWithSameIdentityFieldsInPatientRecord_returnsTrue() {
        patientRecord.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(patientRecord.hasSamePatientName(editedAlice));
    }

    @Test
    public void hasSamePatientIc_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientRecord.hasSamePatientIc(null));
    }

    @Test
    public void hasSamePatientIc_icNotInPatientRecord_returnsFalse() {
        assertFalse(patientRecord.hasSamePatientIc(ALICE));
    }

    @Test
    public void hasSamePatientIc_icInPatientRecord_returnsTrue() {
        patientRecord.addPatient(ALICE);
        Patient patient = new PatientBuilder().withIc(ALICE.getIc().value).build();
        assertTrue(patientRecord.hasSamePatientIc(patient));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> patientRecord.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyPatientRecord whose patients list can violate interface constraints.
     */
    private static class PatientRecordStub implements ReadOnlyPatientRecord {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        PatientRecordStub(Collection<Patient> persons) {
            this.patients.setAll(persons);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }
    }

}
