package seedu.address.model.person.patient;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "Invalid Status";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // valid statuses
        assertTrue(Status.isValidStatus("Inpatient"));
        assertTrue(Status.isValidStatus("Outpatient"));
        assertTrue(Status.isValidStatus("Observation"));
        assertTrue(Status.isValidStatus("Emergency Department"));
        assertTrue(Status.isValidStatus("Intensive Care Unit"));
        assertTrue(Status.isValidStatus("Transitional Care"));
        assertTrue(Status.isValidStatus("inpatient")); // case insensitive
        assertTrue(Status.isValidStatus("InPatient")); // case insensitive

        // invalid statuses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("Invalid Status")); // invalid status string
    }

    @Test
    public void getValue_validStatus_returnsStatus() {
        //valid statuses
        Status status1 = new Status("Inpatient");
        Status status2 = new Status("Outpatient");
        Status status3 = new Status("Observation");
        Status status4 = new Status("Emergency Department");
        Status status5 = new Status("Intensive Care Unit");
        Status status6 = new Status("Transitional Care");

        assertEquals("Inpatient", status1.getValue());
        assertEquals("Outpatient", status2.getValue());
        assertEquals("Observation", status3.getValue());
        assertEquals("Emergency Department", status4.getValue());
        assertEquals("Intensive Care Unit", status5.getValue());
        assertEquals("Transitional Care", status6.getValue());
    }
}
