package seedu.patientist.model.person.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.person.IdNumber;

public class DummyStaffTest {
    @Test
    public void isSamePerson_sameId_true() {
        IdNumber idNumber = new IdNumber("A12345H");
        DummyStaff dummyStaff1 = new DummyStaff(idNumber);
        DummyStaff dummyStaff2 = new DummyStaff(idNumber);

        assertTrue(dummyStaff1.isSamePerson(dummyStaff2));
    }

    @Test
    public void isSamePerson_differentId_false() {
        IdNumber idNumber1 = new IdNumber("A12345G");
        IdNumber idNumber2 = new IdNumber("A12345H");
        DummyStaff dummyStaff1 = new DummyStaff(idNumber1);
        DummyStaff dummyStaff2 = new DummyStaff(idNumber2);

        assertFalse(dummyStaff1.isSamePerson(dummyStaff2));
    }

    @Test
    public void equals() {
        IdNumber idNumber1 = new IdNumber("A12345G");
        IdNumber idNumber2 = new IdNumber("A12345H");
        DummyStaff dummyStaff1 = new DummyStaff(idNumber1);
        DummyStaff dummyStaff2 = new DummyStaff(idNumber2);
        DummyStaff dummyStaff3 = new DummyStaff(idNumber1);

        assertFalse(dummyStaff1.equals(dummyStaff2));
        assertTrue(dummyStaff1.equals(dummyStaff3));
        assertEquals(dummyStaff1, dummyStaff3);
    }
}
