package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        Integer invalidStatus = 1000;
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid statuses
        assertFalse(Status.isValidStatus(4)); // not 0 - 3


        // valid statuses
        assertTrue(Status.isValidStatus(0));
    }

    @Test
    public void testToString() {
        Integer statusIdInterested = 0;
        Integer statusIdApplied = 1;
        Integer statusIdOffered = 2;
        Integer statusIdRejected = 3;

        // valid mapping from status id to status type
        assertEquals((new Status(statusIdInterested)).toString(), "INTERESTED");
        assertEquals((new Status(statusIdApplied)).toString(), "APPLIED");
        assertEquals((new Status(statusIdOffered)).toString(), "OFFERED");
        assertEquals((new Status(statusIdRejected)).toString(), "REJECTED");

    }
}
