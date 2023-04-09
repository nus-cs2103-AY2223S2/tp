package seedu.address.model.person;

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

        // invalid status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("chemical-engineering")); // contains non-alphanumeric characters

        // valid status
        assertTrue(Status.isValidStatus("Year 2 maths")); // alphabets only
        assertTrue(Status.isValidStatus("12345 88888")); // numbers only
        assertTrue(Status.isValidStatus("hi my status is")); // alphanumeric characters
        assertTrue(Status.isValidStatus("Capital economics")); // with capital letters
        assertTrue(Status.isValidStatus("Year 4 renaissance engineering")); // long names
    }
}
