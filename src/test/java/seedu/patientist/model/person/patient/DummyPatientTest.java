package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.patientist.model.person.IdNumber;

public class DummyPatientTest {
    @Test
    public void isSamePerson_sameId_true() {
        IdNumber idNumber = new IdNumber("A12345H");
        DummyPatient dummyPatient1 = new DummyPatient(idNumber);
        DummyPatient dummyPatient2 = new DummyPatient(idNumber);

        assertTrue(dummyPatient1.isSamePerson(dummyPatient2));
    }

    @Test
    public void isSamePerson_differentId_false() {
        IdNumber idNumber1 = new IdNumber("A12345G");
        IdNumber idNumber2 = new IdNumber("A12345H");
        DummyPatient dummyPatient1 = new DummyPatient(idNumber1);
        DummyPatient dummyPatient2 = new DummyPatient(idNumber2);

        assertFalse(dummyPatient1.isSamePerson(dummyPatient2));
    }

    @Test
    public void equals() {
        IdNumber idNumber1 = new IdNumber("A12345G");
        IdNumber idNumber2 = new IdNumber("A12345H");
        DummyPatient dummyPatient1 = new DummyPatient(idNumber1);
        DummyPatient dummyPatient2 = new DummyPatient(idNumber2);
        DummyPatient dummyPatient3 = new DummyPatient(idNumber1);

        assertFalse(dummyPatient1.equals(dummyPatient2));
        assertTrue(dummyPatient1.equals(dummyPatient3));
        assertEquals(dummyPatient1, dummyPatient3);
    }
}
