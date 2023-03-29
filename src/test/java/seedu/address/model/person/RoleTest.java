package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    /*@Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "Nurse";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }*/

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRole("Doctor*")); // contains non-alphanumeric characters

        // valid role
        assertTrue(Role.isValidRole("Doctor")); // Doctor
        assertTrue(Role.isValidRole("Patient")); // Patient
    }
}
