package tfifteenfour.clipboard.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidAddress));
    }

    @Test
    public void isValidStudentId() {
        // null address
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid addresses
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only

        // valid addresses
        assertTrue(StudentId.isValidStudentId("A12345678J"));
    }
}
