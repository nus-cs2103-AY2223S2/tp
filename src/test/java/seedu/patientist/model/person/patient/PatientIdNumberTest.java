package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class PatientIdNumberTest {
    private static PatientIdNumber PID1_UPPER = new PatientIdNumber("A12345B");
    private static PatientIdNumber PID1_LOWER = new PatientIdNumber("a12345b");
    private static PatientIdNumber PID2_UPPER = new PatientIdNumber("C67890D");
    private static PatientIdNumber PID2_LOWER = new PatientIdNumber("c67890d");


    @Test
    public void equals_sameId_True() {
        assertTrue(PID1_UPPER.equals(PID1_UPPER));
        assertTrue(PID1_UPPER.equals(new PatientIdNumber("A12345B")));
    }

    @Test
    public void equals_diffId_False() {
        assertFalse(PID1_UPPER.equals(PID2_UPPER));
    }

    @Test
    public void equals_diffCase_True() {
        assertTrue(PID1_UPPER.equals(PID1_LOWER));
        assertTrue(PID2_LOWER.equals(PID2_UPPER));
    }
}
