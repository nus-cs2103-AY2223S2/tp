package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // blank status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only

        // non-default status values
        assertFalse(Status.isValidStatus("waiting"));
        assertFalse(Status.isValidStatus("interviewing"));
        assertFalse(Status.isValidStatus("technical interview"));
        assertFalse(Status.isValidStatus("have not applied"));
        assertFalse(Status.isValidStatus("rejected."));


        // valid status for the default values only. Case-insensitive
        assertTrue(Status.isValidStatus("new"));
        assertTrue(Status.isValidStatus("applied"));
        assertTrue(Status.isValidStatus("assessment"));
        assertTrue(Status.isValidStatus("interview"));
        assertTrue(Status.isValidStatus("offered"));
        assertTrue(Status.isValidStatus("rejected"));
        assertTrue(Status.isValidStatus("Rejected"));
        assertTrue(Status.isValidStatus("INTERVIEW"));
        assertTrue(Status.isValidStatus("APPlied"));
        assertTrue(Status.isValidStatus("iNTerview"));
        assertTrue(Status.isValidStatus("AcCepted"));
        assertTrue(Status.isValidStatus("ACCEPTED"));
        assertTrue(Status.isValidStatus("accepted"));
    }

    @Test
    public void equals() {
        Status statusOne = new Status("Accepted");
        Status statusTwo = new Status("accepted");
        Status statusThree = new Status("Offered");

        // Same object -> true
        assertEquals(statusOne, statusOne);

        // Different captialisation -> true
        assertEquals(statusOne, statusTwo);

        // Different status -> false
        assertNotEquals(statusOne, statusThree);

        // Not a status -> false
        assertNotEquals(statusOne, "Accepted");
    }
}
