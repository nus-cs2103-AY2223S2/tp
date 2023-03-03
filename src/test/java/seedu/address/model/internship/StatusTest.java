package seedu.address.model.internship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        String invalidStatus = "1000";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid statuses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("a")); // alphabetical
        assertFalse(Status.isValidStatus("$")); // symbols
        assertFalse(Status.isValidStatus("30")); // not 0 - 3


        // valid statuses
        assertTrue(Status.isValidStatus("1"));
        assertTrue(Status.isValidStatus("1 ")); // valid status id with trailing space
    }

    @Test
    public void testToString() {
        String statusIdInterested = "0";
        String statusIdApplied = "1";
        String statusIdOffered = "2";
        String statusIdRejected = "3";

        // valid mapping from status id to status type
        assertEquals((new Status(statusIdInterested)).toString(), "INTERESTED");
        assertEquals((new Status(statusIdApplied)).toString(), "APPLIED");
        assertEquals((new Status(statusIdOffered)).toString(), "OFFERED");
        assertEquals((new Status(statusIdRejected)).toString(), "REJECTED");

    }
    
}
