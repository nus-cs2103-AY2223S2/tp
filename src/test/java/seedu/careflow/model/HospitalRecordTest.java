package seedu.careflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalHospitals.ALEXANDRA_HOSPITAL;
import static seedu.careflow.testutil.TypicalHospitals.VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE;
import static seedu.careflow.testutil.TypicalHospitals.getTypicalHospitalRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.hospital.exceptions.DuplicateHospitalException;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.testutil.HospitalBuilder;

public class HospitalRecordTest {
    private final HospitalRecord hospitalRecord = new HospitalRecord();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hospitalRecord.getHospitalList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalRecord.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHospitalRecord_replacesData() {
        HospitalRecord newData = getTypicalHospitalRecord();
        hospitalRecord.resetData(newData);
        assertEquals(newData, hospitalRecord);
    }

    @Test
    public void resetData_withDuplicateHospitals_throwsDuplicateHospitalException() {
        // Two hospital with the same identity fields
        Hospital editedAlexandraHospital =
                new HospitalBuilder(ALEXANDRA_HOSPITAL).withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        List<Hospital> newHospitals = Arrays.asList(ALEXANDRA_HOSPITAL, editedAlexandraHospital);
        HospitalRecordTest.HospitalRecordStub newData = new HospitalRecordTest.HospitalRecordStub(newHospitals);

        assertThrows(DuplicateHospitalException.class, () -> hospitalRecord.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalRecord.hasHospital(null));
    }

    @Test
    public void hasPatient_patientNotInPatientRecord_returnsFalse() {
        assertFalse(hospitalRecord.hasHospital(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void hasPatient_patientInPatientRecord_returnsTrue() {
        hospitalRecord.addHospital(ALEXANDRA_HOSPITAL);
        assertTrue(hospitalRecord.hasHospital(ALEXANDRA_HOSPITAL));
    }

    @Test
    public void hasHospital_patientWithSameIdentityFieldsInHospitalRecord_returnsTrue() {
        hospitalRecord.addHospital(ALEXANDRA_HOSPITAL);
        Hospital editedAlexandraHospital =
                new HospitalBuilder(ALEXANDRA_HOSPITAL).withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        assertTrue(hospitalRecord.hasHospital(editedAlexandraHospital));
    }

    @Test
    public void getHospitalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hospitalRecord.getHospitalList().remove(0));
    }

    /**
     * A stub ReadOnlyPatientRecord whose patients list can violate interface constraints.
     */
    private static class HospitalRecordStub implements ReadOnlyHospitalRecords {
        private final ObservableList<Hospital> hospitals = FXCollections.observableArrayList();

        HospitalRecordStub(Collection<Hospital> hospitals) {
            this.hospitals.setAll(hospitals);
        }

        @Override
        public ObservableList<Hospital> getHospitalList() {
            return hospitals;
        }
    }

}
