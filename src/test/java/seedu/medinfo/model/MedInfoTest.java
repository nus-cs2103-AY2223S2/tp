package seedu.medinfo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medinfo.testutil.Assert.assertThrows;
import static seedu.medinfo.testutil.TypicalPatients.ALEX;
import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.exceptions.DuplicatePatientException;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.testutil.PatientBuilder;

public class MedInfoTest {

    private final MedInfo medInfo = new MedInfo();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), medInfo.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medInfo.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMedInfo_replacesData() {
        MedInfo newData = getTypicalMedInfo();
        medInfo.resetData(newData);
        assertEquals(newData, medInfo);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALEX).withNric("S1000007A").build();
        List<Patient> newPatients = Arrays.asList(ALEX, editedAlice);
        MedInfoStub newData = new MedInfoStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> medInfo.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medInfo.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInMedInfo_returnsFalse() {
        assertFalse(medInfo.hasPatient(ALEX));
    }

    @Test
    public void hasPerson_personInMedInfo_returnsTrue() {
        medInfo.addPatient(ALEX);
        assertTrue(medInfo.hasPatient(ALEX));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInMedInfo_returnsTrue() {
        medInfo.addPatient(ALEX);
        Patient editedAlice = new PatientBuilder(ALEX).withStatus("YELLOW")
                .build();
        assertTrue(medInfo.hasPatient(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> medInfo.getPatientList().remove(0));
    }

    /**
     * A stub ReadOnlyMedInfo whose patients list can violate interface constraints.
     */
    private static class MedInfoStub implements ReadOnlyMedInfo {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Ward> wards = FXCollections.observableArrayList();
        private final MedInfo medInfo = new MedInfo();

        MedInfoStub(Collection<Patient> patients) {
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

        @Override
        public List<String> getStatsInfo() {
            return medInfo.getStatsInfo();
        }
    }

}
