package seedu.careflow.model.hospital;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalHospitals.ALEXANDRA_HOSPITAL;
import static seedu.careflow.testutil.TypicalHospitals.TAN_TOCK_SENG_HOSPITAL;
import static seedu.careflow.testutil.TypicalHospitals.VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE;
import static seedu.careflow.testutil.TypicalHospitals.VALID_TAN_TOCK_SENG_HOSPITAL_NAME;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Phone;
import seedu.careflow.testutil.HospitalBuilder;

public class HospitalTest {

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hospital(null,
                new Phone(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE)));
        assertThrows(NullPointerException.class, () ->
                new Hospital(new Name(VALID_TAN_TOCK_SENG_HOSPITAL_NAME), null));
    }

    @Test
    public void isSameHospital() {
        // same object -> returns true
        assertTrue(ALEXANDRA_HOSPITAL.isSameHospital(ALEXANDRA_HOSPITAL));

        // null -> returns false
        assertFalse(ALEXANDRA_HOSPITAL.isSameHospital(null));

        // same name, but hotline different -> returns true
        Hospital editedAlexandraHospital = new HospitalBuilder(ALEXANDRA_HOSPITAL)
                .withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        assertTrue(ALEXANDRA_HOSPITAL.isSameHospital(editedAlexandraHospital));

        // different name, hotline same -> returns false
        editedAlexandraHospital = new HospitalBuilder(ALEXANDRA_HOSPITAL)
                .withHospitalName(VALID_TAN_TOCK_SENG_HOSPITAL_NAME).build();
        assertFalse(ALEXANDRA_HOSPITAL.isSameHospital(editedAlexandraHospital));

        // name differs in case, hotline same -> returns false
        Hospital editedTanTockSengHospital =
                new HospitalBuilder(TAN_TOCK_SENG_HOSPITAL)
                        .withHospitalName(VALID_TAN_TOCK_SENG_HOSPITAL_NAME.toLowerCase()).build();
        assertFalse(TAN_TOCK_SENG_HOSPITAL.isSameHospital(editedTanTockSengHospital));

        // name has trailing spaces, hotline same -> returns false
        String nameWithTrailingSpaces = VALID_TAN_TOCK_SENG_HOSPITAL_NAME + " ";
        editedTanTockSengHospital = new HospitalBuilder(TAN_TOCK_SENG_HOSPITAL)
                .withHospitalName(nameWithTrailingSpaces).build();
        assertFalse(TAN_TOCK_SENG_HOSPITAL.isSameHospital(editedTanTockSengHospital));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Hospital alexandraHospitalCopy = new HospitalBuilder(ALEXANDRA_HOSPITAL).build();
        assertTrue(ALEXANDRA_HOSPITAL.equals(alexandraHospitalCopy));

        // same object -> returns true
        assertTrue(ALEXANDRA_HOSPITAL.equals(ALEXANDRA_HOSPITAL));

        // null -> returns false
        assertFalse(ALEXANDRA_HOSPITAL.equals(null));

        // different type -> returns false
        assertFalse(ALEXANDRA_HOSPITAL.equals(5));

        // different hospital -> returns false
        assertFalse(ALEXANDRA_HOSPITAL.equals(TAN_TOCK_SENG_HOSPITAL));

        // different name -> returns false
        Hospital editedAlexandraHospital = new HospitalBuilder(ALEXANDRA_HOSPITAL)
                .withHospitalName(VALID_TAN_TOCK_SENG_HOSPITAL_NAME).build();
        assertFalse(ALEXANDRA_HOSPITAL.equals(editedAlexandraHospital));

        // different hotline -> returns false
        editedAlexandraHospital = new HospitalBuilder(ALEXANDRA_HOSPITAL)
                .withHotline(VALID_TAN_TOCK_SENG_HOSPITAL_HOTLINE).build();
        assertFalse(ALEXANDRA_HOSPITAL.equals(editedAlexandraHospital));
    }
}
