package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.TypicalPatients.ADAM;
import static seedu.patientist.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.PatientBuilder;

class PatientTest {
    /**
     * This tests the ::isSamePatient functionality in Patient.
     * There is no need to test ::equals as in the current implementation
     * ::isSamePatient makes direct use of ::equals.
     */
    @Test
    public void isSamePatient_differentPatient_false() {
        assertFalse(ADAM.isSamePerson(BOB));
    }

    @Test
    public void isSamePatient_samePatient_true() {
        // same object -> return true
        assertTrue(ADAM.isSamePerson(ADAM));
    }

    @Test
    public void isSamePatient_compareToNull_false() {
        // null -> returns false
        assertFalse(ADAM.isSamePerson(null));
    }

    @Test
    public void isSamePatient_sameIdDifferentAttr_true() {
        // same id, different attributes -> returns true
        Patient other = new PatientBuilder()
                .withName(ADAM.getName().toString())
                .withId(ADAM.getPatientIdNumber().toString())
                .build();
        assertTrue(other.isSamePerson(ADAM));
    }

    @Test
    public void isSamePatient_sameIdDifferentCase_true() {
        // same id but input in lowercase -> returns true
        Patient first = new PatientBuilder().withId("a12345b").build();
        Patient second = new PatientBuilder().withId("A12345B").build();
        assertTrue(first.isSamePerson(second));
    }
}
