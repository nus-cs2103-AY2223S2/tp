package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertFalse(Status.isValidStatus("accepted"));
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
    }
}
